package nl.elec332.util.implementationmanager.api;

import java.util.Collection;

/**
 * Created by Elec332 on 19-4-2020
 *
 * Used for selecting the service with the best implementation
 */
public interface IServiceSelector<T> {

    /**
     * @return The type this selector can process
     */
    Class<T> getType();

    /**
     * Determines the best implementation ofg a service and returns it
     *
     * @param implementations All discovered service implementations
     * @return The best service implementation
     */
    T getBestService(Collection<T> implementations);

}
