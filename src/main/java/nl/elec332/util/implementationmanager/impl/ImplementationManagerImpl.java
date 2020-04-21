package nl.elec332.util.implementationmanager.impl;

import nl.elec332.util.implementationmanager.ImplementationManager;
import nl.elec332.util.implementationmanager.api.IServiceSelector;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Elec332 on 21-4-2020
 */
public class ImplementationManagerImpl {

    public static <T> T loadService(Collection<T> allServices, Class<T> type) {
        if (allServices.size() == 1) {
            return allServices.iterator().next();
        }
        IServiceSelector<T> serviceSelector = ServiceSelectorSelector.getServiceSelector(type);
        return Objects.requireNonNull(serviceSelector.getBestService(allServices));
    }

    public static <T> Collection<T> loadServices(Collection<ServiceLoader.Provider<T>> allServices, Class<T> type, String defaultImpl) {
        if (allServices.isEmpty()) {
            if (defaultImpl == null) {
                return null;
            }
            throw new RuntimeException("Impossible?");
        }
        IServiceSelector<T> serviceSelector = ServiceSelectorSelector.getServiceSelector(type);
        return allServices.stream()
                .map(p -> {
                    try {
                        return serviceSelector.loadService(p);
                    } catch (Throwable e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @SuppressWarnings("unchecked")
    public static <T> Collection<ServiceLoader.Provider<T>> loadServiceProviders(Class<T> type, ModuleLayer layer, ClassLoader classLoader, String defaultImpl) {
        if (classLoader == null) {
            classLoader = Thread.currentThread().getContextClassLoader();
        }
        List<ServiceLoader.Provider<T>> providers = new ArrayList<>();

        ImplementationManager.class.getModule().addUses(type);

        ServiceLoader.load(type, classLoader).stream().forEach(providers::add);
        if (layer != null) {
            ServiceLoader.load(layer, type).stream().forEach(providers::add);
        }

        if (defaultImpl != null) {
            defaultImpl = defaultImpl.replace("/", ".");
            boolean isDefaultLoaded = false;
            for (ServiceLoader.Provider<T> provider : providers) {
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
                        providers.add(new ServiceLoader.Provider<>() {

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
