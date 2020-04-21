import nl.elec332.util.implementationmanager.api.IResourceModule;
import nl.elec332.util.implementationmanager.api.IServiceSelector;
import nl.elec332.util.implementationmanager.impl.VersionedServiceSelector;

/**
 * Created by Elec332 on 19-4-2020
 */
module nl.elec332.implementationmanager {

    exports nl.elec332.util.implementationmanager;
    exports nl.elec332.util.implementationmanager.api;

    uses IServiceSelector;
    uses IResourceModule;

    provides IServiceSelector with VersionedServiceSelector;

}