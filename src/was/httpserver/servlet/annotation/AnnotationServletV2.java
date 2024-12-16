package was.httpserver.servlet.annotation;

import was.httpserver.HttpRequest;
import was.httpserver.HttpResponse;
import was.httpserver.HttpServlet;
import was.httpserver.PageNotFoundException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnnotationServletV2 implements HttpServlet {

    private final Map<String, ControllerMethod> methodMap = new HashMap<>();

    public AnnotationServletV2(List<Object> controllers) {
        initializePathMap(controllers);
    }

    @Override
    public void service(HttpRequest request, HttpResponse response) throws IOException {
        String path = request.getPath();

        ControllerMethod controllerMethod = methodMap.get(path);
        if (controllerMethod == null) {
            throw new PageNotFoundException("Requested path '" + path + "' not found");
        }
        controllerMethod.invoke(request, response);
    }

    private void initializePathMap(List<Object> controllers) {
        for (Object controller : controllers) {
            Method[] methods = controller.getClass().getDeclaredMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(Mapping.class)) {
                    Mapping annotation = method.getAnnotation(Mapping.class);
                    if (methodMap.containsKey(annotation.value())) {
                        throw new IllegalArgumentException("Requested path '" + annotation.value() + "' already exists");
                    }
                    methodMap.put(annotation.value(), ControllerMethod.from(controller, method));
                }
            }
        }
    }

    private static class ControllerMethod {
        private final Object controller;
        private final Method method;

        private ControllerMethod(Object controller, Method method) {
            this.controller = controller;
            this.method = method;
        }

        public static ControllerMethod from(Object controller, Method method) {
            return new ControllerMethod(controller, method);
        }

        private void invoke(HttpRequest request, HttpResponse response) {
            Class<?>[] parameterTypes = method.getParameterTypes();
            Object[] args = new Object[parameterTypes.length];

            for (int i = 0; i < parameterTypes.length; i++) {
                if (parameterTypes[i] == HttpRequest.class) {
                    args[i] = request;
                } else if (parameterTypes[i] == HttpResponse.class) {
                    args[i] = response;
                } else {
                    throw new IllegalArgumentException("Unsupported parameter type: " + parameterTypes[i]);
                }
            }

            try {
                method.invoke(controller, args);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
