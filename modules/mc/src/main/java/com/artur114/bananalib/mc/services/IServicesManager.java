package com.artur114.bananalib.mc.services;

public interface IServicesManager {
    void unsubscribe(Class<? extends IService>[] services);
    void unsubscribe(Class<? extends IService> service);
    void subscribe(Class<? extends IService>[] services);
    void subscribe(Class<? extends IService> service);

    void unsubscribe(String... services);
    void unsubscribe(String service);
    void subscribe(String... services);
    void subscribe(String service);

    boolean isSubscribed(Class<? extends IService> service);
    boolean isSubscribed(String service);

    <T extends IService> T findIfSubscribed(Class<T> clazz);
    IService findIfSubscribed(String name);

    <T extends IService> T find(Class<T> clazz);
    IService find(String name);

    void register(Class<?> service);
}
