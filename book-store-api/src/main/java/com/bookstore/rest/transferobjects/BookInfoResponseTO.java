package com.bookstore.rest.transferobjects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * Transfer object to contain book information from book store.
 *
 * @author Anil Jaglan
 * @version 1.0
 */
@JsonInclude(value = Include.NON_NULL)
public class BookInfoResponseTO extends BookBasicInfoTO {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -24781666571133033L;

    /**
     * Total sold copies.
     */
    private int soldCopies;

    /**
     * Unit in stock.
     */
    private int unitsInStock;

    /**
     * 
     * Getter of {@link #soldCopies}.
     * 
     * @return {@link #soldCopies}
     */
    public int getSoldCopies() {
        return soldCopies;
    }

    /**
     * 
     * Setter of {@link #soldCopies}.
     * 
     * @param soldCopies
     *            to be set
     */
    public void setSoldCopies(int soldCopies) {
        this.soldCopies = soldCopies;
    }

    /**
     * 
     * Getter of {@link #unitsInStock}.
     * 
     * @return {@link #unitsInStock}
     */
    public int getUnitsInStock() {
        return unitsInStock;
    }

    /**
     * 
     * Setter of {@link #unitsInStock}.
     * 
     * @param unitsInStock
     *            to be set
     */
    public void setUnitsInStock(int unitsInStock) {
        this.unitsInStock = unitsInStock;
    }

    /**
     * 
     * {@inheritDoc}
     */

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("BookInfoTO [basicInfo=");
        builder.append(super.toString());
        builder.append(", soldCopies");
        builder.append(soldCopies);
        builder.append(", unitsInStock");
        builder.append(unitsInStock);
        builder.append("]");
        return builder.toString();
    }

}
