package com.ettrade.ngbss.jasperreport.service;

import com.ettrade.ngbss.jasperreport.common.APIFileStreamResponse;
import com.ettrade.ngts.commons.bean.ReturnCode;
import com.ettrade.ngts.trading.api.bean.JasperReportRequest;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeAll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReportServiceTest {
    protected static final Logger log = LoggerFactory.getLogger(ReportServiceTest.class);
    protected static ReportService reportService;
    protected final String BASE_DIRECTORY = "C:\\log\\";

    /**
     * 启动读取spring的配置文件
     * @throws InterruptedException
     */
    @BeforeAll
    public static void beforeAll() throws InterruptedException {
        /*final CountDownLatch latch = new CountDownLatch(1);
        final Server server =
            (Server) AppLoader.createApplication(
                Framework.Provider.SPRING,
                new XmlPathConfiguration(AdminServer.class.getSimpleName(), "com/ettrade/ngts/trading/server.xml"),
                AdminServer.class.getSimpleName());

        ListenableFuture<Service.State> future = server.start();

        Futures.addCallback(future, new FutureCallback<Service.State>() {
            public void onSuccess(Service.State state) {
                if (state.equals(Server.State.RUNNING)) {
                    latch.countDown();
                }
            }

            public void onFailure(Throwable thrown) {
                log.error("Exception occurred when starting server [{}]", server.getInstanceName(), thrown);
            }
        });

        latch.await();

        reportService = App.service("ReportService");*/
    }

    public void generateMultipleReportAsync(Map<String, JasperReportRequest> requestMap, boolean isValidate)
        throws ExecutionException, InterruptedException {
        Map<String, CompletableFuture<APIFileStreamResponse>> futureMap = new HashMap<>();
        for (String description : requestMap.keySet()) {
            JasperReportRequest request = requestMap.get(description);

            if (isValidate) {
                reportService.validateRequest(request);
            }

            CompletableFuture<APIFileStreamResponse> future = reportService.report(request);
            futureMap.put(description, future);
        }

        for (String description : futureMap.keySet()) {
            CompletableFuture<APIFileStreamResponse> future = futureMap.get(description);

            APIFileStreamResponse response = future.get();
            assertEquals(ReturnCode.NoErr.getValue(), response.getReturnCode());
            writeFileToDisk(response, description);
        }
    }

    // 通过 reportService 来进行报表的生成， 启动的时候会读取相关的spring配置，达到启动时类中已经有了各项属性的效果，目前这个是不完整的项目，所以启动不了，只是用来做一个例子
    public void assertAndGenerateReport(JasperReportRequest request, String description) {
        CompletableFuture<APIFileStreamResponse> future = reportService.report(request);
        try {
            APIFileStreamResponse response = future.get();
            assertEquals(ReturnCode.NoErr.getValue(), response.getReturnCode());
            writeFileToDisk(response, description);

        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void writeFileToDisk(APIFileStreamResponse response, String description) {
        try {
            log.debug("response returnCode: {}", response.getReturnCode());

            String[] tmp = response.getFileName().split("\\.");
            String fileName = tmp[0];
            String extension = tmp[1];
            File targetFile =
                new File(Paths.get(BASE_DIRECTORY, fileName + "_" + description + "." + extension).toString());
            InputStream inputStream = response.getInputStream();
            FileUtils.copyInputStreamToFile(inputStream, targetFile);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
