package com.artur114.bananalib.mc.services;

import com.artur114.bananalib.mc.services.client.BlockHighlightService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BananaServicesManager implements IServicesManager {
    private static final Logger log = LogManager.getLogger("B-SERVICES");
    private final Map<Class<? extends IService>, IService> serviceClassMap = new HashMap<>();
    private final Map<String, IService> serviceMap = new HashMap<>();
    private final Set<IService> subscribed = new HashSet<>();

    @Override
    public void unsubscribe(Class<? extends IService>[] services) {
        for (Class<? extends IService> name : services) {
            this.unsubscribe(name);
        }
    }

    @Override
    public void subscribe(Class<? extends IService>[] services) {
        for (Class<? extends IService> name : services) {
            this.subscribe(name);
        }
    }

    @Override
    public void unsubscribe(Class<? extends IService> service) {
        IService s = this.serviceClassMap.get(service);
        if (s != null && this.subscribed.contains(s)) {
            s.unsubscribe();
            this.subscribed.remove(s);
        }
    }

    @Override
    public void subscribe(Class<? extends IService> service) {
        IService s = this.serviceClassMap.get(service);
        if (s == null) {
            log.warn(" Attempt to subscribe a unknown service: {}", service);
            return;
        }
        if (this.subscribed.contains(s)) {
            log.warn(" Attempt to double subscribe a service: {}", service);
            return;
        }
        s.subscribe();
        this.subscribed.add(s);
    }

    @Override
    public void unsubscribe(String... services) {
        for (String name : services) {
            this.unsubscribe(name);
        }
    }

    @Override
    public void subscribe(String... services) {
        for (String name : services) {
            this.subscribe(name);
        }
    }

    @Override
    public void unsubscribe(String service) {
        IService s = this.serviceMap.get(service);
        if (s != null && this.subscribed.contains(s)) {
            s.unsubscribe();
            this.subscribed.remove(s);
        }
    }

    @Override
    public void subscribe(String service) {
        IService s = this.serviceMap.get(service);
        if (s == null) {
            log.warn("Attempt to subscribe a unknown service: {}", service);
            return;
        }
        if (this.subscribed.contains(s)) {
            log.warn("Attempt to double subscribe a service: {}", service);
            return;
        }
        s.subscribe();
        this.subscribed.add(s);
    }

    @Override
    public boolean isSubscribed(Class<? extends IService> service) {
        return this.subscribed.contains(this.serviceClassMap.get(service));
    }

    @Override
    public boolean isSubscribed(String service) {
        return this.subscribed.contains(this.serviceMap.get(service));
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends IService> T findIfSubscribed(Class<T> clazz) {
        IService s = this.serviceClassMap.get(clazz);
        if (!this.subscribed.contains(s)) return null;
        return (T) s;
    }

    @Override
    public IService findIfSubscribed(String name) {
        IService s = this.serviceMap.get(name);
        if (!this.subscribed.contains(s)) return null;
        return s;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends IService> T find(Class<T> clazz) {
        return (T) this.serviceClassMap.get(clazz);
    }

    @Override
    public IService find(String name) {
        return this.serviceMap.get(name);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void register(Class<?> service) {
        Service ann = service.getAnnotation(Service.class);
        boolean flag = false;

        if (ann != null && IService.class.isAssignableFrom(service)) {
            try {
                IService ser = (IService) service.getDeclaredConstructor().newInstance();
                this.serviceMap.put(ann.value(), ser);
                this.serviceClassMap.put((Class<? extends IService>) service, ser);
                flag = true;
            } catch (Exception ignored) {}
        } else {
            for (Field field : service.getDeclaredFields()) {
                if (!Modifier.isStatic(field.getModifiers()) || !IService.class.isAssignableFrom(field.getType())) {
                    continue;
                }
                Service an = field.getAnnotation(Service.class);
                if (an != null) {
                    boolean as = field.isAccessible();
                    field.setAccessible(true);
                    try {
                        IService ser = (IService) field.get(null);
                        this.serviceMap.put(an.value(), ser);
                        this.serviceClassMap.put((Class<? extends IService>) field.getType(), ser);
                        flag = true;
                    } catch (IllegalAccessException ignored) {}
                    field.setAccessible(as);
                }
            }
        }

        if (!flag) {
            log.warn("failed to register service {}", service);
        }
    }
}
