package com.bookstore.common.converter;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Abstract Converter class which implements {@code Converter<S,T>}
 *
 * @author Anil Jaglan
 * @version 1.0
 */
public abstract class AbstractConverter<S, T> implements Converter<S, T> {

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public List<T> convert(List<S> source) {
        final List<T> target = new ArrayList<>();
        if (source != null && !source.isEmpty()) {
            source.forEach(s -> target.add(convert(s)));
        }
        return target;
    }

}
