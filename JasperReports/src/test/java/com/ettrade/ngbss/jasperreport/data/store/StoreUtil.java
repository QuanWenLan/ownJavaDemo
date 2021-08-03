package com.ettrade.ngbss.jasperreport.data.store;

import com.ettrade.ngbss.service.TServerDBConnectionService;
import com.ettrade.ngts.App;
import com.ettrade.ngts.commons.bean.ReturnCode;
import com.ettrade.ngts.trading.api.service.exception.JasperReportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// utilties class to simplify finding DatabaseStore
public class StoreUtil {
    private static Logger log = LoggerFactory.getLogger(StoreUtil.class);
    private static final TServerDBConnectionService storeService = App.service("TServerDBConnectionService");

    public static DatabaseStore getTSStore(String epId) {
        DatabaseStore databaseStore = storeService.getDatabaseStore(epId);
        if (databaseStore == null) {
            log.debug("databaseStore using epId, {}, is empty", epId);
            throw new JasperReportException(ReturnCode.TServerDBNotFound);
        }
        return databaseStore;
    }

    // ... later we can have getAuthServerStore(), getXXXStore() and etc, this is just a utilities method to simplify finding DBStore
}
