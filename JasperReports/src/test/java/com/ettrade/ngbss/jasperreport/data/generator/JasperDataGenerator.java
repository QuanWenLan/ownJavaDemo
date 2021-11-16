package com.ettrade.ngbss.jasperreport.data.generator;

import com.ettrade.ngbss.jasperreport.common.JasperReportContext;
import com.ettrade.ngbss.jasperreport.data.source.JasperDataSource;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public interface JasperDataGenerator<T> {

    static final ExecutorService executorService = Executors.newFixedThreadPool(10);

    CompletableFuture<JasperDataSource<T>> generate(JasperReportContext context);
}
