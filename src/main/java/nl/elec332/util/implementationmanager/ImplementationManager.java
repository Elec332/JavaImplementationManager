package nl.elec332.util.implementationmanager;

import nl.elec332.util.implementationmanager.impl.ImplementationManagerImpl;

import java.util.Collection;
import java.util.ServiceLoader;
import java.util.ServiceLoader.Provider;

/**
 * Created by Elec332 on 17-4-2020
 */
public class ImplementationManager {

    public static <T> T loadService(Class<T> type) {
        return loadService(type, (ClassLoader) null);
    }

    public static <T> T loadService(Class<T> type, ModuleLayer layer) {
        return loadService(type, layer, (String) null);
    }

    public static <T> T loadService(Class<T> type, ClassLoader classLoader) {
        return loadService(type, classLoader, null);
    }

    public static <T> T loadService(Class<T> type, ModuleLayer layer, String defaultImpl) {
        return loadService(type, layer, null, defaultImpl);
    }

    public static <T> T loadService(Class<T> type, ClassLoader classLoader, String defaultImpl) {
        return loadService(type, null, classLoader, defaultImpl);
    }

    public static <T> T loadService(Class<T> type, ModuleLayer layer, ClassLoader classLoader) {
        return loadService(type, layer, classLoader, null);
    }

    public static <T> T loadService(Class<T> type, ModuleLayer layer, ClassLoader classLoader, String defaultImpl) {
        return ImplementationManagerImpl.loadService(loadServices(type, layer, classLoader, defaultImpl), type);
    }

    public static <T> Collection<T> loadServices(Class<T> type) {
        return loadServices(type, (ClassLoader) null);
    }

    public static <T> Collection<T> loadServices(Class<T> type, ModuleLayer layer) {
        return loadServices(type, layer, (String) null);
    }

    public static <T> Collection<T> loadServices(Class<T> type, ClassLoader classLoader) {
        return loadServices(type, classLoader, null);
    }

    public static <T> Collection<T> loadServices(Class<T> type, ModuleLayer layer, String defaultImpl) {
        return loadServices(type, layer, null, defaultImpl);
    }

    public static <T> Collection<T> loadServices(Class<T> type, ClassLoader classLoader, String defaultImpl) {
        return loadServices(type, null, classLoader, defaultImpl);
    }

    public static <T> Collection<T> loadServices(Class<T> type, ModuleLayer layer, ClassLoader classLoader) {
        return loadServices(type, layer, classLoader, null);
    }

    public static <T> Collection<T> loadServices(Class<T> type, ModuleLayer layer, ClassLoader classLoader, String defaultImpl) {
        return ImplementationManagerImpl.loadServices(loadServiceProviders(type, layer, classLoader, defaultImpl), type, defaultImpl);
    }

    public static <T> Collection<Provider<T>> loadServiceProviders(Class<T> type) {
        return loadServiceProviders(type, (ClassLoader) null);
    }

    public static <T> Collection<Provider<T>> loadServiceProviders(Class<T> type, ModuleLayer layer) {
        return loadServiceProviders(type, layer, (String) null);
    }

    public static <T> Collection<Provider<T>> loadServiceProviders(Class<T> type, ClassLoader classLoader) {
        return loadServiceProviders(type, classLoader, null);
    }

    public static <T> Collection<Provider<T>> loadServiceProviders(Class<T> type, ModuleLayer layer, String defaultImpl) {
        return loadServiceProviders(type, layer, null, defaultImpl);
    }

    public static <T> Collection<Provider<T>> loadServiceProviders(Class<T> type, ClassLoader classLoader, String defaultImpl) {
        return loadServiceProviders(type, null, classLoader, defaultImpl);
    }

    public static <T> Collection<Provider<T>> loadServiceProviders(Class<T> type, ModuleLayer layer, ClassLoader classLoader) {
        return loadServiceProviders(type, layer, classLoader, null);
    }

    public static <T> Collection<ServiceLoader.Provider<T>> loadServiceProviders(Class<T> type, ModuleLayer layer, ClassLoader classLoader, String defaultImpl) {
        return ImplementationManagerImpl.loadServiceProviders(type, layer, classLoader, defaultImpl);
    }

}
