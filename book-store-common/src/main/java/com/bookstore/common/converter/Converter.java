package com.bookstore.common.converter;

import java.util.List;

/**
 * 
 * Generic Converter interface to convert source to target object.
 *
 * @author Anil Jaglan
 * @version 1.0
 */
public interface Converter<S, T> {

    /**
     * 
     * Method to convert source object to target object.
     * 
     * @param source
     *            - Source object.
     * @return - Converted target object.
     */
    T convert(S source);

    /**
     * 
     * Method to convert source object to target object.
     * 
     * @param source
     *            - List of source objects.
     * @return - Converted list of target objects.
     */
    List<T> convert(List<S> source);
}
