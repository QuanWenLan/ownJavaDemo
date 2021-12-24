package com.ett.report;

import net.sf.jasperreports.engine.JRDataSource;

import java.util.Map;

public class JRDataSourceInitiator {

    public static JRDataSource createAndInit(
        Class<? extends AbstractHibernateQueryResultDataSource> dsCls,
        Map<String, Object> parameterMap) {
        DefaultJSubReportContext context = new DefaultJSubReportContext();
        context.putAll(parameterMap);
        AbstractHibernateQueryResultDataSource dsInstance;
        try {
            dsInstance = dsCls.newInstance();
            int res = dsInstance.initDataSource(context);
            if (res != JasperReportEngine.NO_DATA && res != JasperReportEngine.CREATE_SUCCESSFUL)
                throw new Exception("init sub report datasource error.");
            return dsInstance;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
