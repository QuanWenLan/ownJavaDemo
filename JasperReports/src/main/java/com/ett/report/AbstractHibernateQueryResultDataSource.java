package com.ett.report;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import org.apache.commons.lang3.StringUtils;

import java.util.Iterator;
import java.util.Objects;

public abstract class AbstractHibernateQueryResultDataSource implements OFTPJRDataSource {

    protected String[] fields;
    protected Iterator<Object[]> iterator;
    protected Object currentValue;

    public Object getFieldValue(JRField field) throws JRException {
        Object value = null;
        int index = -1;
        try {
            index = getFieldIndex(field.getName());
        }
        catch (Exception e) {
            throw new JRException(e);
        }
        if (index > -1) {
            Object[] values = (Object[]) currentValue;
            value = values[index];
        }
        return value;
    }

    public boolean next() throws JRException {
        currentValue = iterator.hasNext() ? iterator.next() : null;
        return (currentValue != null);
    }

    private int getFieldIndex(String field) {
        Objects.requireNonNull(iterator, "DataSource No initialization has yet been made.");
        int index = -1;
        for (int i = 0; i < fields.length; i++) {
            if (StringUtils.equalsIgnoreCase(fields[i], field)) {
                index = i;
                break;
            }
        }
        return index;
    }

}
