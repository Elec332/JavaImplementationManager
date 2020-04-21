package nl.elec332.util.implementationmanager;

import nl.elec332.util.implementationmanager.api.IServiceSelector;

import java.util.Collection;
import java.util.Comparator;

/**
 * Created by Elec332 on 19-4-2020
 * <p>
 * Abstract service selector for services that can easily be compared
 * Will always return the max value
 */
public abstract class AbstractComparableServiceSelector<T> implements IServiceSelector<T> {

    public AbstractComparableServiceSelector(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    private final Comparator<T> comparator;

    @Override
    public final T getBestService(Collection<T> implementations) {
        return implementations.stream().max(comparator).orElseThrow(NullPointerException::new);
    }

}
