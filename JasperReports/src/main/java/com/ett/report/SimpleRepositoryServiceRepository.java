package com.ett.report;

import com.google.common.collect.Maps;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.repo.InputStreamResource;
import net.sf.jasperreports.repo.ReportResource;
import net.sf.jasperreports.repo.RepositoryService;
import net.sf.jasperreports.repo.Resource;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class SimpleRepositoryServiceRepository implements RepositoryService {

    private Map<String, Pair<String, JasperReport>> cachedCompileReport = Maps.newHashMap();

    private JasperReport loadReport(final String fileName) throws IOException, JRException {
        if (StringUtils.endsWith(fileName, "jrxml")) {
            try (InputStream in = this.getClass().getClassLoader().getResourceAsStream(fileName);
                InputStream inMd5 = this.getClass().getClassLoader().getResourceAsStream(fileName)) {
                String md5 = DigestUtils.md5Hex(inMd5);
                Pair<String, JasperReport> cache = cachedCompileReport.get(fileName);
                if (cache != null && StringUtils.equals(md5, cache.getLeft())) {
                    return cache.getRight();
                }
                else {
                    JasperReport res = JasperCompileManager.compileReport(in);
                    if (cache == null) {
                        cache = Pair.of(md5, res);
                        cachedCompileReport.put(fileName, cache);
                    }
                    else {
                        cache.setValue(res);
                    }
                    return res;
                }
            }
        }
        else {
            try (InputStream in = loadReportAsStream(fileName)) {
                return (JasperReport) JRLoader.loadObject(in);
            }
        }
    }

    private InputStream loadReportAsStream(final String fileName) {
        return this.getClass().getClassLoader().getResourceAsStream(fileName);
    }

    @Override
    public Resource getResource(final String uri) {
        return null;
    }

    @Override
    public void saveResource(final String uri, final Resource resource) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <K extends Resource> K getResource(final String uri, final Class<K> resourceType) {
        try {
            if (InputStreamResource.class == resourceType) {
                InputStreamResource inputStreamResource = new InputStreamResource();
                inputStreamResource.setInputStream(loadReportAsStream(uri));
                return resourceType.cast(inputStreamResource);
            }
            else if (ReportResource.class == resourceType) {
                final ReportResource reportResource = new ReportResource();
                reportResource.setReport(loadReport(uri));
                return resourceType.cast(reportResource);
            }
        }
        catch (Exception e1) {
            return null;
        }
        return null;
    }
}
