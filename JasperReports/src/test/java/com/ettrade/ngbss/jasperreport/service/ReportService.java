package com.ettrade.ngbss.jasperreport.service;

import com.ettrade.ngbss.jasperreport.common.*;
import com.ettrade.ngbss.jasperreport.service.exception.JasperReportException;
import com.ettrade.ngts.commons.bean.ReturnCode;
import com.ettrade.ngts.trading.api.bean.JasperReportRequest;
import com.google.common.io.ByteStreams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Validator;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

// if want to use App.service, needs to extends ett service
public class ReportService {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    // set it up using Spring Dependency Injection
    private Map<JasperReportId, JasperReportGenerator> reportGeneratorMap;  // ID

    public void validateRequest(JasperReportRequest reportRequest) throws JasperReportException {
        BeanPropertyBindingResult errors = new BeanPropertyBindingResult(reportRequest, "jasperReportRequest");
        Validator validator = reportRequest.getReport().getValidator();
        validator.validate(reportRequest, errors);
        if (errors.hasErrors()) {
            // return async exception seems better, but how to best handle it?
            // let see if it work first
            throw new JasperReportException(errors);
        }
    }

    /**
     *  生成报表逻辑，reportGeneratorMap 会根据jasperreport.xml来进行注入，具体的 JasperReportGenerator 是配置进行的，不过
     * @param reportRequest
     * @return
     */
    public CompletableFuture<APIFileStreamResponse> report(JasperReportRequest reportRequest) {
        JasperReportGenerator reportGenerator = reportGeneratorMap.get(reportRequest.getReport());
        log.debug("reportGenerator used template: {}", reportGenerator.getResourcePath());

        JasperReportContext reportContext = new JasperReportContext();
        reportContext.setLocale(reportRequest.getLocale());
        reportContext.setFileType(reportRequest.getFileType());
        reportContext.setFilter(reportRequest.getFilter());
        reportContext.setSorting(reportRequest.getSorting());
//        reportContext.setEpId(reportRequest.getEpId());
        // todo in AdminServer need to set Actor as well

        if (reportGenerator == null) {
            APIFileStreamResponse response = new APIFileStreamResponse();
            response.setReturnCode(ReturnCode.NoReportSupplied.getValue());
            return CompletableFuture.completedFuture(response);
        }
        else {
            log.debug("starts retrieving jasperReportResult");
            CompletableFuture<JasperReportResult> reportResultFuture = reportGenerator.generate(reportContext);
            return reportResultFuture.thenApply(reportResult -> {
                log.debug("finish retrieving jasperReportResult");
                APIFileStreamResponse response = new APIFileStreamResponse();
                response.setReturnCode(reportResult.getReturnCode().getValue());
                response.setFileName(reportResult.getFileName());
                response.setInputStream(reportResult.getInputStream());
                response.setContentType(reportResult.getContentType());
                return response;
            });
        }
    }

    public CompletableFuture<APIFileResponse> report(Collection<JasperReportRequest> reportRequests) {
        List<CompletableFuture<APIFileStreamResponse>> futureList = reportRequests.stream().map(request -> {
            return report(request);
        }).collect(Collectors.toList());

        return CompletableFuture.allOf(futureList.toArray(new CompletableFuture[futureList.size()])).thenApplyAsync(
            (empty) -> {
                // TODO replace zipFile location, to a dynamic one, specify in Spring XML or in database like eStatement
                // e.g. ssadm.storage_record ?

                try {
                    File tmpZipFile = new File("report.zip");
                    log.debug("tmpZipFile: {}", tmpZipFile.toPath().toString());

                    // source: https://stackoverflow.com/questions/22260151/zip-multiple-objects-from-inputstream-in-java
                    try (ZipOutputStream zos =
                        new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(tmpZipFile)))) {
                        for (CompletableFuture<APIFileStreamResponse> fileStreamResponseFuture : futureList) {
                            APIFileStreamResponse fileStreamResponse = fileStreamResponseFuture.get();
                            String fileName = fileStreamResponse.getFileName();
                            InputStream inputStream = fileStreamResponse.getInputStream();

                            ZipEntry ze = new ZipEntry(fileName);
                            zos.putNextEntry(ze);
                            ByteStreams.copy(inputStream, zos);
                            inputStream.close();
                            zos.closeEntry();
                        }
                    }

                    String fileName = "bundle.zip";
                    APIFileResponse fileResponse = new APIFileResponse();
                    fileResponse.setFile(tmpZipFile);
                    fileResponse.setHeader("Content-Type", Files.probeContentType(tmpZipFile.toPath()));
                    fileResponse.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
                    fileResponse.setReturnCode(ReturnCode.NoErr.getValue());
                    return fileResponse;

                }
                catch (Exception ex) {
                    ex.printStackTrace();
                    if (ex instanceof JasperReportException) {
                        throw (JasperReportException) ex;
                    }
                    else {
                        throw new JasperReportException(ReturnCode.Undefined);
                    }
                }
            });
    }

    public Map<JasperReportId, JasperReportGenerator> getReportGeneratorMap() {
        return reportGeneratorMap;
    }

    public void setReportGeneratorMap(Map<JasperReportId, JasperReportGenerator> reportGeneratorMap) {
        this.reportGeneratorMap = reportGeneratorMap;
    }

}
