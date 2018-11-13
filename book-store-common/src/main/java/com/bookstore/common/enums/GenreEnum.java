package com.bookstore.common.enums;

import com.bookstore.common.exceptions.BookStoreExceptionEnum;
import com.bookstore.common.exceptions.ExtendedRuntimeException;

/**
 * 
 * Enum class to define Book genre list.
 *
 * @author Anil Jaglan
 * @version 1.0
 */
public enum GenreEnum {

    COMPUTER("Computer"), FANTASY("Fantacy"), HORROR("Horror"), ROMANCE("Romance");

    /**
     * Genre name in Camel case.
     */
    protected String name;

    /**
     * 
     * Constructor.
     *
     * @param name
     *            - Genre name in camel case.
     */
    private GenreEnum(final String name) {
        this.name = name;
    }

    /**
     * 
     * Method to get Genre name in camel case.
     * 
     * @return String
     */
    public String getName() {
        return this.name();
    }

    /**
     * 
     * Method to return GenreEnum by name.
     * 
     * @param name
     * @return
     */
    public static GenreEnum getByName(final String name) {
        for (final GenreEnum genre : GenreEnum.values()) {
            if (genre.getName().equalsIgnoreCase(name)) {
                return genre;
            }
        }
        throw new ExtendedRuntimeException(BookStoreExceptionEnum.INVALID_GENRE_NAME);
    }
}
