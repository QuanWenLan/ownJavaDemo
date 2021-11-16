package com.ettrade.ngbss.jasperreport.data.store;

import com.ettrade.ngbss.jasperreport.data.store.util.ResultSetObjectMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.function.BiFunction;

public class DatabaseStore {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public DatabaseStore(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public NamedParameterJdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public <T> List<T> query(String sql, SqlParameterSource paramSource, RowMapper<T> rowMapper)
        throws DataAccessException {
        return jdbcTemplate.query(sql, paramSource, rowMapper);
    }

    public <T> List<T> query(String sql, RowMapper<T> rowMapper) throws DataAccessException {
        return jdbcTemplate.query(sql, rowMapper);
    }

    public <T, U> List<U> simpleQuery(
        String sql,
        SqlParameterSource paramSource,
        Class<U> convertedClazz,
        final BiFunction<U, ResultSet, U> customConverter) {
        return jdbcTemplate.query(sql, paramSource, new RowMapper<U>() {
            public U mapRow(ResultSet rs, int rowNum) throws SQLException {
                // auto convert db value to java object using fieldName and camelCase convert to snakeCase
                U details = ResultSetObjectMapper.convert(rs, convertedClazz);
                // for those field that needs custom mapping, you can do it here as well
                // since
                return customConverter == null ? details : customConverter.apply(details, rs);
            }
        });
    }

    public <T, U> List<U> simpleQuery(String sql, SqlParameterSource paramSource, Class<U> convertedClazz) {
        return simpleQuery(sql, paramSource, convertedClazz, null);
    }
}
