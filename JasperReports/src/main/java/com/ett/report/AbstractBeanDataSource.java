package com.ett.report;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class AbstractBeanDataSource implements OFTPJRDataSource {

    private static String INFOMSG = "please initialize delegateDataSource in initDataSource(context)";

    protected JRBeanCollectionDataSource delegateDataSource;
    private static int SQL_IN_CAPACITY = 800;

    @Override
    public boolean next() throws JRException {
        Objects.requireNonNull(delegateDataSource, INFOMSG);
        return delegateDataSource.next();
    }

    @Override
    public Object getFieldValue(JRField jrField) throws JRException {
        Objects.requireNonNull(delegateDataSource, INFOMSG);
        return delegateDataSource.getFieldValue(jrField);
    }

    protected String formatSqlInForNative(List<String> source, String key) {
        StringBuffer getInSql = new StringBuffer();
        getInSql.append(" (");
        int inSizeI = source.size() / SQL_IN_CAPACITY, inSizeJ = source.size() % SQL_IN_CAPACITY;
        int totalBlock = inSizeJ > 0 ? inSizeI + 1 : inSizeI;
        for (int i = 0; i < totalBlock; i++) {
            int endPost = (i + 1) * SQL_IN_CAPACITY;
            if (endPost > source.size()) {
                endPost = source.size();
            }
            getInSql.append(
                    source.subList(i * SQL_IN_CAPACITY, endPost).stream().collect(Collectors.joining("', '", "'", "'")));
            getInSql.append(")");
            if (i < totalBlock - 1) {
                getInSql.append(" or ");
                getInSql.append(key);
                getInSql.append(" in (");
            }
        }
        //getInSql.append(" )");
        return getInSql.toString();
    }

}
