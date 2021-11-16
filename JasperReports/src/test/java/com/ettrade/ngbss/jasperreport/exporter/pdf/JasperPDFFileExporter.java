package com.ettrade.ngbss.jasperreport.exporter.pdf;

import com.ettrade.ngbss.jasperreport.common.JasperReportResult;
import com.ettrade.ngbss.jasperreport.exporter.JasperFileExporter;
import com.ettrade.ngts.commons.bean.ReturnCode;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.PdfExporterConfiguration;
import net.sf.jasperreports.export.PdfReportConfiguration;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class JasperPDFFileExporter implements JasperFileExporter {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    private PdfReportConfiguration reportConfiguration;
    private PdfExporterConfiguration exporterConfiguration;

    public PdfReportConfiguration getReportConfiguration() {
        return reportConfiguration;
    }

    public void setReportConfiguration(PdfReportConfiguration reportConfiguration) {
        this.reportConfiguration = reportConfiguration;
    }

    public PdfExporterConfiguration getExporterConfiguration() {
        return exporterConfiguration;
    }

    public void setExporterConfiguration(PdfExporterConfiguration exporterConfiguration) {
        this.exporterConfiguration = exporterConfiguration;
    }

    @Override
    public JasperReportResult export(JasperPrint print) throws JRException {
        log.debug("export jasperPrint");
        JasperReportResult result = new JasperReportResult();

        JRPdfExporter exporter = new JRPdfExporter();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        exporter.setExporterInput(new SimpleExporterInput(print));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));

        // report configuration
        if (getReportConfiguration() != null) {
            exporter.setConfiguration(getReportConfiguration());
        }
        // exporter configuration, like PDF password and etc
        if (getExporterConfiguration() != null) {
            exporter.setConfiguration(getExporterConfiguration());
        }

        exporter.exportReport();

        InputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        result.setReturnCode(ReturnCode.NoErr);
        result.setInputStream(inputStream);
        result.setContentType("application/pdf");
        return result;
    }
}
