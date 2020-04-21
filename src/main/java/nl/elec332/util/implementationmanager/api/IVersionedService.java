package nl.elec332.util.implementationmanager.api;

import nl.elec332.util.implementationmanager.impl.VersionedServiceSelector;

/**
 * Created by Elec332 on 19-4-2020
 *
 * A versioned service which is handled by {@link VersionedServiceSelector}
 * The service with the highest version will always be used
 */
public interface IVersionedService {

    default long version() {
        return 0;
    }

}
