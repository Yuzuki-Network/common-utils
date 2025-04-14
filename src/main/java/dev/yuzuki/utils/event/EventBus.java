package dev.yuzuki.utils.event;

import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class EventBus {
    private final Map<Class<?>, List<ListenerHolder>> methodMap = new ConcurrentHashMap<>();
    private final ExecutorService service = Executors.newFixedThreadPool(16);

    public void register(Object o) {
        Arrays.stream(o.getClass().getDeclaredMethods())
                .filter(m -> m.isAnnotationPresent(Listener.class))
                .filter(m -> m.getParameterCount() == 1)
                .peek(m -> m.setAccessible(true))
                .forEach(m -> {
                    Class<?> paramType = m.getParameterTypes()[0];
                    methodMap.computeIfAbsent(paramType, k -> new ArrayList<>())
                            .add(new ListenerHolder(o, m, m.getAnnotation(Listener.class).parallel()));
                });

        methodMap.replaceAll((key, list) ->
                list.stream()
                        .sorted(Comparator.comparing(h -> h.method.getAnnotation(Listener.class).priority()))
                        .collect(Collectors.toList()));
    }

    public void unregister(Object o) {
        methodMap.replaceAll((key, list) ->
                list.stream()
                        .filter(holder -> !holder.instance.equals(o))
                        .collect(Collectors.toList()));
    }

    public void post(Object event) {
        List<ListenerHolder> listeners = methodMap.get(event.getClass());
        if (listeners == null) return;

        if (event instanceof ParallelEvent) {
            service.execute(() -> listeners.forEach(holder -> {
                try {
                    holder.method.invoke(holder.instance, event);
                } catch (Exception ignored) { }
            }));
        } else {
            listeners.forEach(holder -> {
                try {
                    holder.method.invoke(holder.instance, event);
                } catch (Exception ignored) { }
            });
        }
    }

    public void post(Object... event) {
        for (Object o : event) {
            post(o);
        }
    }

    private static class ListenerHolder {
        final Object instance;
        final Method method;
        final boolean parallel;

        ListenerHolder(Object instance, Method method, boolean parallel) {
            this.instance = instance;
            this.method = method;
            this.parallel = parallel;
        }
    }
}