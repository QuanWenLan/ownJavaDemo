package com.ettrade.ngbss.jasperreport.exporter.excel;

import com.ettrade.ngbss.jasperreport.common.JasperReportResult;
import com.ettrade.ngbss.jasperreport.exporter.JasperFileExporter;
import com.ettrade.ngts.commons.bean.ReturnCode;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.XlsExporterConfiguration;
import net.sf.jasperreports.export.XlsReportConfiguration;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class JasperExcelFileExporter implements JasperFileExporter {
    private XlsReportConfiguration reportConfiguration;
    private XlsExporterConfiguration exporterConfiguration;

    public XlsReportConfiguration getReportConfiguration() {
        return reportConfiguration;
    }

    public void setReportConfiguration(XlsReportConfiguration reportConfiguration) {
        this.reportConfiguration = reportConfiguration;
    }

    public XlsExporterConfiguration getExporterConfiguration() {
        return exporterConfiguration;
    }

    public void setExporterConfiguration(XlsExporterConfiguration exporterConfiguration) {
        this.exporterConfiguration = exporterConfiguration;
    }

    @Override
    public JasperReportResult export(JasperPrint print) throws JRException {
        JasperReportResult result = new JasperReportResult();

        JRXlsExporter exporter = new JRXlsExporter();
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
        result.setContentType("application/vnd.ms-excel");
        return result;
    }
}
