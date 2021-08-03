package com.ettrade.ngbss.jasperreport.common;

import com.ettrade.ngbss.jasperreport.data.generator.JasperDataGenerator;
import com.ettrade.ngbss.jasperreport.data.source.JasperDataSource;
import com.ettrade.ngbss.jasperreport.exporter.JasperFileExporter;
import com.ettrade.ngbss.jasperreport.exporter.excel.DefaultJasperExcelFileExporter;
import com.ettrade.ngbss.jasperreport.exporter.pdf.DefaultJasperPDFFileExporter;
import com.ettrade.ngbss.jasperreport.service.exception.JasperReportException;
import com.ettrade.ngts.commons.bean.ReturnCode;
import net.sf.jasperreports.engine.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Paths;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class JasperReportGenerator<T> {
    protected Logger log = LoggerFactory.getLogger(this.getClass());

    protected String fileName; // fileName used for download
    protected String resourcePath; // location where the jrxml locates 也是根据jasperreport.xml指定位置配置
    protected JasperDataGenerator<T> generator;
    // by default, it uses standard JasperFileExporter for All Type, you may override it using Spring Dependency injection
    protected Map<JasperFileType, JasperFileExporter> fileExporterMap =
        new HashMap<JasperFileType, JasperFileExporter>() {
            {
                put(JasperFileType.PDF, new DefaultJasperPDFFileExporter());
                put(JasperFileType.EXCEL, new DefaultJasperExcelFileExporter());
            }
        };

    public CompletableFuture<JasperReportResult> generate(JasperReportContext context) {
        try {
            // todo should not be compile every time, should try using ETWealth approach, which cache the compile result for reuse
            String template = Paths.get(resourcePath, "template.jrxml").toString();
            String localeBasePath = resourcePath.replaceAll("/", ".") + ".locale";

            final JasperReport report =
                JasperCompileManager.compileReport(this.getClass().getClassLoader().getResourceAsStream(template));
            // async way to generate report, as it may require time on getting some necessary data remmtely
            context.setLocaleBasePath(localeBasePath);

            log.debug("starts retrieving data for JasperDataReport");
            CompletableFuture<JasperDataSource<T>> future = generator.generate(context); // 调用具体的类，通过spring的xml文件配置的
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
                    }
                    else if (source instanceof JRDataSource) {
                        print = JasperFillManager.fillReport(report, paramMap, JRDataSource.class.cast(source));
                    }
                    else if (source instanceof Connection) {
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
                }
                catch (JRException ex) {
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
                }
                else {
                    log.debug("JasperReport unexpected error: {}", ex.getMessage());
                    result.setReturnCode(ReturnCode.Unknown);
                }
                return result;
            });
        }
        catch (JRException ex) {
            log.debug("JasperReport unexpected error: {}", ex.getMessage());
            return CompletableFuture.completedFuture(
                new JasperReportResult().setReturnCode(ReturnCode.JasperReportGenerationError));
        }
        catch (Exception ex) {
            log.debug("JasperReport unexpected error: {}", ex.getMessage());
            return CompletableFuture.completedFuture(new JasperReportResult().setReturnCode(ReturnCode.Unknown));
        }
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getResourcePath() {
        return resourcePath;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    public JasperDataGenerator<T> getGenerator() {
        return generator;
    }

    public void setGenerator(JasperDataGenerator<T> generator) {
        this.generator = generator;
    }

    public Map<JasperFileType, JasperFileExporter> getFileExporterMap() {
        return fileExporterMap;
    }

    public void setFileExporterMap(Map<JasperFileType, JasperFileExporter> fileExporterMap) {
        this.fileExporterMap = fileExporterMap;
    }
}
