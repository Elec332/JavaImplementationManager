package nl.elec332.util.implementationmanager.impl;

import nl.elec332.util.implementationmanager.AbstractComparableServiceSelector;
import nl.elec332.util.implementationmanager.api.IExtensionImplementation;

import java.util.Comparator;

/**
 * Created by Elec332 on 21-4-2020
 */
public class ExtensionImplementationManager extends AbstractComparableServiceSelector<IExtensionImplementation> {

    public ExtensionImplementationManager() {
        super(Comparator.comparingInt(e -> e.getImplementationType().getSpeed(e.getImplementationSpeed())));
    }

    @Override
    public Class<IExtensionImplementation> getType() {
        return IExtensionImplementation.class;
    }

}
