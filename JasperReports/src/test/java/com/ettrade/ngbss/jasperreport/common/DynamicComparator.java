package com.ettrade.ngbss.jasperreport.common;

import com.ettrade.ngbss.jasperreport.data.datastruct.common.BaseStruct;

import java.lang.reflect.Field;
import java.util.*;

/**
 * This comment is copied from Comparator
 *
 * It is generally the case, but <i>not</i> strictly required that
 * <tt>(compare(x, y)==0) == (x.equals(y))</tt>.  Generally speaking,
 * any comparator that violates this condition should clearly indicate
 * this fact.  The recommended language is "Note: this comparator
 * imposes orderings that are inconsistent with equals."
 */
public class DynamicComparator<T extends BaseStruct> implements Comparator<T> {
    private Class<T> clazz;
    private List<SortKey> sortKeys = new ArrayList<>();
    private final Map<String, Field> fieldMap = new HashMap<>();

    public DynamicComparator(Class<T> clazz) {
        this.clazz = clazz;
        // this class may be have a base class so we need get all the fields including the base class
        getDeclaredFieldsForClass(this.clazz);
    }

    private void getDeclaredFieldsForClass(Class<T> clazz) {
        Class<? super T> superclass = clazz.getSuperclass();
        if(superclass != null) {
            getDeclaredFieldsForClass((Class<T>) superclass);
        }
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            fieldMap.put(field.getName(), field);
        }
    }

    public List<SortKey> getSortKeys() {
        return sortKeys;
    }

    public DynamicComparator<T> setSortKeys(List<SortKey> sortKeys) {
        this.sortKeys = sortKeys;
        return this;
    }

    @Override
    public int compare(T o1, T o2) {
        // do not assume T is null, but if it is, put it in the end
        if (o1 == null) {
            return 1;
        }
        else if (o2 == null) {
            return -1;
        }

        for (SortKey sortKey : sortKeys) {
            String fieldName = sortKey.getAttribute();
            SortOrder sortOrder = sortKey.getOrder();

            // todo is it unsafe to do that?
            Field field = fieldMap.get(fieldName); // may throw NullPointerException, but as it is the same class, I think it is okay
            Object fieldA = null;
            Object fieldB = null;
            try {
                fieldA = field.get(o1);
                fieldB = field.get(o2);
            }
            catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("cannot get fieldValue using Reflection on field: " + fieldName);
            }

            if (fieldA == null) {
                return sortOrder == SortOrder.ASCENDING ? -1 : 1;
            }
            else if (fieldB == null) {
                return sortOrder == SortOrder.ASCENDING ? 1 : -1;
            }
            if (fieldA instanceof Comparable) {
                int compareValue = ((Comparable) fieldA).compareTo((Comparable) fieldB);
                if (compareValue != 0) {
                    boolean isAsc = sortOrder == SortOrder.ASCENDING;
                    return isAsc ? compareValue : -compareValue;
                }
            }
            else {
                throw new RuntimeException(
                    String.format(
                        "Cannot handle Field that is not implementing Comparable, with className %s, and fieldname: %s",
                        clazz.getSimpleName(),
                        fieldName));
            }
        }

        return 0;
    }
}