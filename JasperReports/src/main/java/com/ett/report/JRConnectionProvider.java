package com.ett.report;

import java.sql.Connection;

public interface JRConnectionProvider {

    public Connection provide() throws Exception;

}
