package nl.elec332.util.implementationmanager.api;

/**
 * Created by Elec332 on 21-4-2020
 */
public interface IExtensionImplementation {

    /**
     * Returns the implementation type on this implementation.
     *
     * @return The implementation type on this implementation
     */
    ImplementationType getImplementationType();

    /**
     * Returns the speed relative to the implementation type.
     * When two implementations of the same type are present, the one with the highest speed is chosen.
     *
     * @return The relative speed of this implementation compared to its {@link ImplementationType}
     */
    default int getImplementationSpeed() {
        return 0;
    }

}
