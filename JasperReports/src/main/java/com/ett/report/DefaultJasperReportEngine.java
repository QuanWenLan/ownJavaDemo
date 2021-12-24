package com.ett.report;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.repo.RepositoryService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

public class DefaultJasperReportEngine implements JasperReportEngine {
    /**
    * Logger for this class
    */
    private static final Logger logger = LoggerFactory.getLogger(DefaultJasperReportEngine.class);

    @Override
    public int createReport(JRContext context) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        try {
            logger.debug("start create report, template = {}.", context.getTemplate());
            beforeCreate(context);
            int retCode = doCreate(context);
            afterCreate(context, retCode);
            return retCode;
        }
        catch (Exception e) {
            logger.error("error create jr report.", e);
            onException(context, e);
            return CREATE_FAILED;
        }
        finally {
            stopWatch.stop();
            logger.debug("finish create report, cost {} milliseconds.", stopWatch.getTime());
        }
    }

    protected void beforeCreate(JRContext context) {

    }

    protected void afterCreate(JRContext context, int createStatus) {
    }

    protected void onException(JRContext context, Exception e) {

    }

    public int doCreate(JRContext context) {
        Connection conn = null;
        int res = CREATE_SUCCESSFUL;
        try {
            JasperReport report =
                JasperCompileManager.compileReport(
                    this.getClass().getClassLoader().getResourceAsStream(context.getTemplate()));
            Map<String, Object> paraMap = Maps.newHashMap();
            context.entrySet().forEach(entry -> {
                String key = entry.getKey();
                Object value = entry.getValue();
                if (value != null) {
                    if (value.getClass() == String.class) {
                        if (StringUtils.isNotBlank(value.toString())) {
                            paraMap.put(key, value);
                        }
                    }
                    else {
                        paraMap.put(key, value);
                    }
                }
            });
            conn = context.getConnectionProvider().provide();
            paraMap.put("REPORT_CONNECTION", conn);
            paraMap.put(REPORT_PARAMETER_PRICE_PATTERN, context.getPricePattern());
            paraMap.put(REPORT_PARAMETER_QTY_PATTERN, context.getQtyPattern());
            paraMap.put(REPORT_PARAMETER_AMT_PATTERN, context.getAmtPattern());
            paraMap.put(REPORT_PARAMETER_EXCHANGE_RATE_PATTERN, context.getExchangeRatePattern());
            paraMap.put(REPORT_PARAMETER_RATE_PATTERN, context.getRatePattern());
            SimpleJasperReportsContext jasperReportsContext = new SimpleJasperReportsContext();
            jasperReportsContext.setExtensions(
                RepositoryService.class,
                Lists.newArrayList(new SimpleRepositoryServiceRepository()));
            JasperPrint print = null;
            if (context.getJRDataSourceClass() != null) {
                Class<? extends OFTPJRDataSource> dsClass = context.getJRDataSourceClass();
                OFTPJRDataSource dsInstance = dsClass.newInstance();
                res = dsInstance.initDataSource(context);
                if (res != CREATE_SUCCESSFUL)
                    throw new RuntimeException("Failed to init data source.");
                print = JasperFillManager.getInstance(jasperReportsContext).fill(report, paraMap, dsInstance);
            }
            else {
                print = JasperFillManager.getInstance(jasperReportsContext).fill(report, paraMap, conn);
            }
            context.getExporter().export(print);
        }
        catch (FileNotFoundException e) {
            logger.error("Report template cannot find : <>", context.getTemplate());
            logger.error(e.getMessage(), e);
            res = TEMPLATE_NOT_FOUND;
        }
        catch (JRException e1) {
            logger.error("JRException:", e1);
            res = CREATE_FAILED;
        }
        catch (Exception e2) {
            if (res != NO_DATA)
                logger.error("Exception:", e2);
            if (res == CREATE_SUCCESSFUL)
                res = CREATE_FAILED;
        }
        finally {
            logger.debug("Close connection");
            if (conn != null)
                try {
                    conn.close();
                }
                catch (SQLException e) {
                    logger.error("Close connection occured exception.", e);
                }
        }
        return res;
    }

}
