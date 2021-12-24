package com.ett.report;


import java.util.HashMap;

public class DefaultJSubReportContext extends HashMap<String, Object> implements JRContext, JRConstants {

    private static final long serialVersionUID = 1L;

    public ReportMimeType getReportMimeType() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getTemplate() {
        throw new UnsupportedOperationException();
    }

    @Override
    public JRConnectionProvider getConnectionProvider() {
        throw new UnsupportedOperationException();
    }

    @Override
    public JRExporter getExporter() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getSystemId() {
//        return SystemConstant.getInstance().getSystemId();
        return null;
    }

/*    @Override
    public LoginUser getActor() {
        return null;
    }*/

    @Override
    public Class<? extends AbstractHibernateQueryResultDataSource> getJRDataSourceClass() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getPricePattern() {
        return REPORT_PARAMETER_PRICE_PATTERN_VALUE;
    }

    @Override
    public String getAmtPattern() {
        return REPORT_PARAMETER_AMT_PATTERN_VALUE;
    }

    @Override
    public String getQtyPattern() {
        return REPORT_PARAMETER_QTY_PATTERN_VALUE;
    }

    @Override
    public String getExchangeRatePattern() {
        return REPORT_PARAMETER_EXCHANGE_RATE_PATTERN_VALUE;
    }

    @Override
    public String getRatePattern() {
        return REPORT_PARAMETER_RATE_PATTERN_VALUE;
    }

}
