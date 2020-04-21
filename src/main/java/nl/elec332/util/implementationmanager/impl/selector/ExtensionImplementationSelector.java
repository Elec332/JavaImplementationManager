package nl.elec332.util.implementationmanager.impl.selector;

import nl.elec332.util.implementationmanager.AbstractComparableServiceSelector;
import nl.elec332.util.implementationmanager.api.IExtensionImplementation;

import java.util.Comparator;

/**
 * Created by Elec332 on 21-4-2020
 */
public class ExtensionImplementationSelector extends AbstractComparableServiceSelector<IExtensionImplementation> {

    public ExtensionImplementationSelector() {
        super(Comparator.comparingInt(e -> e.getImplementationType().getSpeed(e.getImplementationSpeed())));
    }

    @Override
    public Class<IExtensionImplementation> getType() {
        return IExtensionImplementation.class;
    }

}
