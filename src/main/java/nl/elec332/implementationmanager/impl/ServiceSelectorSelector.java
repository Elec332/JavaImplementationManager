package nl.elec332.implementationmanager.impl;

import nl.elec332.implementationmanager.api.IServiceSelector;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

/**
 * Created by Elec332 on 19-4-2020
 *
 * Used for determining the best {@link IServiceSelector} service selector for a given type
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class ServiceSelectorSelector {

    private static final ServiceLoader<IServiceSelector> serviceSelectorLoader;

    public static <T> IServiceSelector<T> getServiceSelector(Class<T> type) {
        serviceSelectorLoader.reload();
        List<IServiceSelector> selectors = serviceSelectorLoader.stream()
                .map(ServiceLoader.Provider::get)
                .collect(Collectors.toList());
        List<IServiceSelector> specificSelectors = selectors.stream()
                .filter(s -> s.getType() == type || type.isAssignableFrom(s.getType()))
                .collect(Collectors.toList());
        if (!specificSelectors.isEmpty()) {
            return selectMostSpecific(specificSelectors);
        }
        specificSelectors = selectors.stream()
                .filter(s -> s.getType().isAssignableFrom(type))
                .collect(Collectors.toList());
        if (!specificSelectors.isEmpty()) {
            return selectMostSpecific(specificSelectors);
        }
        throw new UnsupportedOperationException("No selector found for type " + type.getCanonicalName());
    }

    private static IServiceSelector selectMostSpecific(Collection<IServiceSelector> selectors) {
        Iterator<IServiceSelector> it = selectors.iterator();
        IServiceSelector ret = it.next();
        while (it.hasNext()) {
            IServiceSelector match = it.next();
            if (!match.getType().isAssignableFrom(ret.getType())) {
                ret = match;
            }
        }
        return ret;
    }

    static {
        serviceSelectorLoader = ServiceLoader.load(IServiceSelector.class);
    }

}
