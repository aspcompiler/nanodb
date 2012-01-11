package edu.caltech.nanodb.expressions;


import java.io.Serializable;
import java.util.ArrayList;

import edu.caltech.nanodb.relations.Tuple;


/**
 * A simple implementation of the {@link Tuple} interface for storing literal
 * tuple values.
 */
public class TupleLiteral implements Tuple, Serializable {

    /** The actual values of the columns in the tuple. **/
    private ArrayList<Object> values;


    /**
     * Construct a new tuple-literal that initially has zero columns.  Column
     * values can be added with the {@link #addValue} method, or entire tuples
     * can be appended using the {@link #appendTuple} method.
     */
    public TupleLiteral() {
        values = new ArrayList<Object>();
    }


    /**
     * Construct a new tuple-literal with the specified number of columns, each
     * of which is initialized to the SQL <tt>NULL</tt> (Java <tt>null</tt>)
     * value.  Each column's type-information is also set to <tt>null</tt>.
     *
     * @param numCols the number of columns to create for the tuple
     */
    public TupleLiteral(int numCols) {
        values = new ArrayList<Object>(numCols);
        for (int i = 0; i < numCols; i++)
            values.add(null);
    }


    /**
     * Constructs a new tuple-literal that is a copy of the specified tuple.
     * After construction, the new tuple-literal object can be manipulated in
     * various ways, just like all tuple-literals.
     *
     * @param tuple the tuple to make a copy of
     */
    public TupleLiteral(Tuple tuple) {
        this();
        appendTuple(tuple);
    }


    /**
     * In-memory tuples are cacheable!
     *
     * @return <tt>true</tt> because in-memory tuples are always cacheable.
     */
    public boolean isCacheable() {
        return true;
    }


    /**
     * Appends the specified value to the end of the tuple-literal.
     *
     * @param value the value to append.  This is allowed to be <tt>null</tt>.
     */
    public void addValue(Object value) {
        values.add(value);
    }


    /**
     * Appends the specified tuple's contents to this tuple-literal object.
     *
     * @param tuple the tuple data to copy into this tuple-literal
     *
     * @throws IllegalArgumentException if <tt>tuple</tt> is <tt>null</tt>.
     */
    public void appendTuple(Tuple tuple) {
        if (tuple == null)
            throw new IllegalArgumentException("tuple cannot be null");

        for (int i = 0; i < tuple.getColumnCount(); i++)
            values.add(tuple.getColumnValue(i));
    }


    // Let javadoc copy the comments.
    @Override
    public int getColumnCount() {
        return values.size();
    }

    // Let javadoc copy the comments.
    @Override
    public boolean isNullValue(int colIndex) {
        return (values.get(colIndex) == null);
    }

    // Let javadoc copy the comments.
    @Override
    public Object getColumnValue(int colIndex) {
        return values.get(colIndex);
    }

    // Let javadoc copy the comments.
    @Override
    public void setColumnValue(int colIndex, Object value) {
        values.set(colIndex, value);
    }
}