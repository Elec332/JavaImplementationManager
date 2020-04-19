package nl.elec332.implementationmanager;

import nl.elec332.implementationmanager.api.IServiceSelector;
import nl.elec332.implementationmanager.impl.ServiceSelectorSelector;

import java.util.*;
import java.util.ServiceLoader.Provider;
import java.util.stream.Collectors;

/**
 * Created by Elec332 on 17-4-2020
 */
public class ImplementationManager {

    public static <T> T loadService(Class<T> type){
        return loadService(type, (ClassLoader) null);
    }

    public static <T> T loadService(Class<T> type, ModuleLayer layer){
        return loadService(type, layer, (String) null);
    }

    public static <T> T loadService(Class<T> type, ClassLoader classLoader){
        return loadService(type, classLoader, null);
    }

    public static <T> T loadService(Class<T> type, ModuleLayer layer, String defaultImpl){
        return loadService(type, layer, null, defaultImpl);
    }

    public static <T> T loadService(Class<T> type, ClassLoader classLoader, String defaultImpl){
        return loadService(type, null, classLoader, defaultImpl);
    }

    public static <T> T loadService(Class<T> type, ModuleLayer layer, ClassLoader classLoader){
        return loadService(type, layer, classLoader, null);
    }

    public static <T> T loadService(Class<T> type, ModuleLayer layer, ClassLoader classLoader, String defaultImpl) {
        Collection<Provider<T>> allServices = loadServices(type, layer, classLoader, defaultImpl);
        if (allServices.isEmpty()) {
            if (defaultImpl == null) {
                return null;
            }
            throw new RuntimeException("Impossible?");
        }
        if (allServices.size() == 1) {
            return allServices.iterator().next().get();
        }
        IServiceSelector<T> serviceSelector = ServiceSelectorSelector.getServiceSelector(type);
        return Objects.requireNonNull(serviceSelector.getBestService(allServices.stream().map(Provider::get).collect(Collectors.toList())));
    }

    public static <T> Collection<Provider<T>> loadServices(Class<T> type){
        return loadServices(type, (ClassLoader) null);
    }

    public static <T> Collection<Provider<T>> loadServices(Class<T> type, ModuleLayer layer){
        return loadServices(type, layer, (String) null);
    }

    public static <T> Collection<Provider<T>> loadServices(Class<T> type, ClassLoader classLoader){
        return loadServices(type, classLoader, null);
    }

    public static <T> Collection<Provider<T>> loadServices(Class<T> type, ModuleLayer layer, String defaultImpl){
        return loadServices(type, layer, null, defaultImpl);
    }

    public static <T> Collection<Provider<T>> loadServices(Class<T> type, ClassLoader classLoader, String defaultImpl){
        return loadServices(type, null, classLoader, defaultImpl);
    }

    public static <T> Collection<Provider<T>> loadServices(Class<T> type, ModuleLayer layer, ClassLoader classLoader){
        return loadServices(type, layer, classLoader, null);
    }

    @SuppressWarnings("unchecked")
    public static <T> Collection<Provider<T>> loadServices(Class<T> type, ModuleLayer layer, ClassLoader classLoader, String defaultImpl) {
        if (classLoader == null) {
            classLoader = Thread.currentThread().getContextClassLoader();
        }
        List<Provider<T>> providers = new ArrayList<>();

        ServiceLoader.load(type, classLoader).stream().forEach(providers::add);
        if (layer != null) {
            ServiceLoader.load(layer, type).stream().forEach(providers::add);
        }

        if (defaultImpl != null) {
            defaultImpl = defaultImpl.replace("/", ".");
            boolean isDefaultLoaded = false;
            for (Provider<T> provider : providers) {
                if (provider.type().getName().equals(defaultImpl)) {
                    isDefaultLoaded = true;
                    break;
                }
            }
            if (!isDefaultLoaded) {
                try {
                    Class<?> clazz = classLoader.loadClass(defaultImpl);
                    if (type.isAssignableFrom(clazz)) {
                        T impl = (T) clazz.getConstructor().newInstance();
                        providers.add(new Provider<>() {

                            @Override
                            public Class<? extends T> type() {
                                return (Class<? extends T>) clazz;
                            }

                            @Override
                            public T get() {
                                return impl;
                            }
                        });
                    } else {
                        throw new IllegalArgumentException(defaultImpl + " is not a valid implementation of " + type.getCanonicalName());
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
        if (providers.isEmpty()) {
            return Collections.emptyList();
        }
        return providers;
    }

}
