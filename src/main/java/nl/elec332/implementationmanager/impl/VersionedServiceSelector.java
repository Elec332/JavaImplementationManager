package nl.elec332.implementationmanager.impl;

import nl.elec332.implementationmanager.AbstractComparableServiceSelector;
import nl.elec332.implementationmanager.api.IVersionedService;

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
