package com.ett.report;

//import com.etwealth.mo.server.response.BaseReportResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.AbstractXlsReportConfiguration;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class BonusPointXLSReportExporter implements JRExporter {
    /**
     * Logger for this class
     */
    private static final Logger logger = LoggerFactory.getLogger(BonusPointXLSReportExporter.class);

//    private BaseReportResponse response;

    private boolean removeEmptySpaceBetweenRows = Boolean.FALSE;

/*    public BonusPointXLSReportExporter withResponse(BaseReportResponse response) {
        this.response = response;
        return this;
    }*/

    public BonusPointXLSReportExporter() {
    }

    public BonusPointXLSReportExporter(boolean removeEmptySpaceBetweenRows) {
        this.removeEmptySpaceBetweenRows = removeEmptySpaceBetweenRows;
    }

    @Override
    public void export(JasperPrint print) {
        ByteArrayOutputStream pdfOut = new ByteArrayOutputStream();
        try {
            JRXlsExporter exporter = new JRXlsExporter();
            exporter.setExporterInput(new SimpleExporterInput(print));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(pdfOut));
            // do not set the default white background and remove the empty between the rows and the empty between the columns
            AbstractXlsReportConfiguration xlsReportConfiguration = new AbstractXlsReportConfiguration();
            xlsReportConfiguration.setWhitePageBackground(Boolean.FALSE);
            xlsReportConfiguration.setRemoveEmptySpaceBetweenColumns(Boolean.TRUE);
            if (removeEmptySpaceBetweenRows) {
                xlsReportConfiguration.setRemoveEmptySpaceBetweenRows(Boolean.TRUE);
            }
            xlsReportConfiguration.setDetectCellType(Boolean.TRUE);
            exporter.setConfiguration(xlsReportConfiguration);
            exporter.exportReport();
        } catch (JRException e) {
            logger.error("could not export ReportToXLSStream.", e);
            IOUtils.closeQuietly(pdfOut);
            throw new RuntimeException(e);
        }
        InputStream pdfInputStream = new ByteArrayInputStream(pdfOut.toByteArray());
//        response.setReportInputStream(pdfInputStream);
    }

}
