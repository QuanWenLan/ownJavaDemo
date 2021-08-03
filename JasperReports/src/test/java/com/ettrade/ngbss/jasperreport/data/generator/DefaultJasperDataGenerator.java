package com.ettrade.ngbss.jasperreport.data.generator;

import com.ettrade.ngbss.jasperreport.common.JasperReportContext;
import com.ettrade.ngbss.jasperreport.common.Pattern;
import com.ettrade.ngbss.jasperreport.data.source.JasperDataSource;
import net.sf.jasperreports.engine.JRParameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;

public abstract class DefaultJasperDataGenerator<T> implements JasperDataGenerator<T> {
    protected Logger log = LoggerFactory.getLogger(this.getClass());
    // remarks, no attribute can be specified here, as this class is going to reused, unless the resource is static
    // e.g. if we have ResourceBundle specify here, if there are many request, each uses different locales, error may happen,
    // if the subclass uses the resourceBundles to generate String values sent to JasperReport [then it may use different locale as expected]

    @Override
    public final CompletableFuture<JasperDataSource<T>> generate(JasperReportContext context) {
        return CompletableFuture.supplyAsync(() -> getDefaultParameters(context)).thenCompose(defaultParams -> {
            JasperDataSource<T> source = new JasperDataSource<>();
            source.setParameterMap(defaultParams);
            return doGenerate(context, source);
        });
    }

    protected abstract CompletableFuture<JasperDataSource<T>> doGenerate(
        JasperReportContext context,
        JasperDataSource<T> dataSource);

    private Map<String, Object> getDefaultParameters(JasperReportContext context) {
        String epId = context.getEpId();

        Map<String, Object> params = new HashMap<>();

        Locale locale = context.getLocale() != null ? context.getLocale() : Locale.ENGLISH;
        ResourceBundle resourceBundle = ResourceBundle.getBundle(context.getLocaleBasePath(), locale);
        params.put(JRParameter.REPORT_LOCALE, locale);
        params.put(JRParameter.REPORT_RESOURCE_BUNDLE, resourceBundle);

        // set default format here
        params.put(Pattern.COST_PATTERN.name(), "#,##0.####");
        params.put(Pattern.PRICE_PATTERN.name(), "#,##0.###");
        params.put(Pattern.QTY_PATTERN.name(), "#,##0");
        params.put(Pattern.AMT_PATTERN.name(), "#,##0.##");
        params.put(Pattern.DATE_PATTERN.name(), "dd-MMM-yyyy");
        params.put(Pattern.DATE_TIME_PATTERN.name(), "dd-MMM-yyyy HH:mm:ss");

        params.put("companyName", "aaaa");

        return params;
    }
}
