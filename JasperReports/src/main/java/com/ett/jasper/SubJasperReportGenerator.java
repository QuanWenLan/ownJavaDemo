package com.ett.jasper;

/*import com.ettrade.ngbss.jasperreport.data.source.JasperDataSource;
import com.ettrade.ngts.commons.bean.ReturnCode;
import com.ettrade.ngts.trading.api.service.exception.JasperReportException;*/
import net.sf.jasperreports.engine.*;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.nio.file.Paths;
import java.sql.Connection;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

/**
 * @author Vin lan
 * @className SubJasperReportGenerator
 * @description TODO
 * @createTime 2020-10-19  16:19
 **/
public class SubJasperReportGenerator<T> extends JasperReportGenerator<T> {

   /* @Override
    public CompletableFuture<JasperReportResult> generate(JasperReportContext context) {
        try {
            // todo should not be compile every time, should try using ETWealth approach, which cache the compile result for reuse
            String template = Paths.get(resourcePath, "template.jrxml").toString();
            String localeBasePath = resourcePath.replaceAll("/", ".") + ".locale";

            final JasperReport report =
                    JasperCompileManager.compileReport(this.getClass().getClassLoader().getResourceAsStream(template));
            try {
                ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
                Resource[] resources = resolver.getResources("classpath:" + resourcePath + "/template_*.jrxml");
                for (Resource resource : resources) {
                    String filename = resource.getFilename();
                    filename = filename.substring(0, filename.lastIndexOf(".") + 1) + "jasper";
                    JasperCompileManager.compileReportToFile(Paths.get(resource.getURI()).toString(), filename);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            context.setLocaleBasePath(localeBasePath);
            CompletableFuture<JasperDataSource<T>> future = generator.generate(context);
            log.debug("starts retrieving data for JasperDataReport");

            return future.thenApply((JasperDataSource<T> dataSource) -> {
                log.debug("finish retrieving data for JasperDataReport");
                // set default language as us, if not given
                T source = dataSource.getSource();
                JasperPrint print = null;
                Map<String, Object> paramMap = dataSource.getParameterMap();
                // may based on different locale to change font
                //                Locale locale = (Locale) paramMap.get(JRParameter.REPORT_LOCALE);
                report.getDefaultStyle().setFontName("ARIALUNI");
                try {
                    if (source == null) {
                        print = JasperFillManager.fillReport(report, paramMap);
                    } else if (source instanceof JRDataSource) {
                        print = JasperFillManager.fillReport(report, paramMap, JRDataSource.class.cast(source));
                    } else if (source instanceof Connection) {
                        print = JasperFillManager.fillReport(report, paramMap, Connection.class.cast(source));
                    }

                    if (print == null) {
                        // throw JasperPrint Exception
                        log.debug("JasperPrint is null with source class: {}", source.getClass().getName());
                        throw new JasperReportException(ReturnCode.JasperReportGenerationError);
                    }

                    // export JasperPrint to different file type
                    JasperFileType fileType = context.getFileType();

                    JasperReportResult result = fileExporterMap.get(fileType).export(print);
                    result.setFileName(fileName, context.getFileType());
                    return result;
                } catch (JRException ex) {
                    ex.printStackTrace();
                    log.error("JRException caught: {}", ex.getMessage());
                    throw new JasperReportException(ReturnCode.JasperReportGenerationError);
                }
            }).exceptionally(ex -> {
                log.debug("inside exceptionally, {}", ex.getMessage());
                ex.printStackTrace();

                // convert CompletionException to original Exception first
                if (ex instanceof CompletionException) {
                    Throwable throwable = ((CompletionException) ex).getCause();
                    ex = throwable;
                }

                JasperReportResult result = new JasperReportResult();
                if (ex instanceof JasperReportException) {
                    ReturnCode returnCode = ((JasperReportException) ex).getCode();
                    log.debug("JasperReport JasperReportException: {}", returnCode);
                    result.setReturnCode(returnCode);
                } else {
                    log.debug("JasperReport unexpected error: {}", ex.getMessage());
                    result.setReturnCode(ReturnCode.Unknown);
                }
                return result;
            });
        } catch (JRException ex) {
            log.debug("JasperReport unexpected error: {}", ex.getMessage());
            return CompletableFuture.completedFuture(
                    new JasperReportResult().setReturnCode(ReturnCode.JasperReportGenerationError));
        } catch (Exception ex) {
            log.debug("JasperReport unexpected error: {}", ex.getMessage());
            return CompletableFuture.completedFuture(new JasperReportResult().setReturnCode(ReturnCode.Unknown));
        }
    }*/
}
