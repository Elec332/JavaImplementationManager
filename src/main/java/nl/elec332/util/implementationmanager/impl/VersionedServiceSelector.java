package nl.elec332.util.implementationmanager.impl;

import nl.elec332.util.implementationmanager.AbstractComparableServiceSelector;
import nl.elec332.util.implementationmanager.api.IVersionedService;

import java.util.Comparator;

/**
 * Created by Elec332 on 19-4-2020
 *
 * Service selector for {@link IVersionedService}
 */
public class VersionedServiceSelector extends AbstractComparableServiceSelector<IVersionedService> {

    public VersionedServiceSelector() {
        super(Comparator.comparingLong(IVersionedService::version));
    }

    @Override
    public Class<IVersionedService> getType() {
        return IVersionedService.class;
    }

}
