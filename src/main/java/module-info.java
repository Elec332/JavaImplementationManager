import nl.elec332.implementationmanager.api.IResourceModule;
import nl.elec332.implementationmanager.api.IServiceSelector;

/**
 * Created by Elec332 on 19-4-2020
 */
module nl.elec332.implementationmanager {

    exports nl.elec332.implementationmanager;
    exports nl.elec332.implementationmanager.api;

    uses IServiceSelector;
    uses IResourceModule;

}