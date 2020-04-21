package nl.elec332.util.implementationmanager.api;

import java.util.Collection;
import java.util.ServiceLoader;

/**
 * Created by Elec332 on 19-4-2020
 * <p>
 * Used for selecting the service with the best implementation
 */
public interface IServiceSelector<T> {

    /**
     * @return The type this selector can process
     */
    Class<T> getType();

    /**
     * Load the provided service.
     * If the service can't be loaded for any reason, this method should return null
     *
     * @param provider The provider to be loaded
     * @return The loaded service
     */
    default T loadService(ServiceLoader.Provider<T> provider) {
        try {
            return provider.get();
        } catch (Throwable t) {
            return null;
        }
    }

    /**
     * Determines the best implementation ofg a service and returns it
     *
     * @param implementations All discovered service implementations
     * @return The best service implementation
     */
    T getBestService(Collection<T> implementations);

}
