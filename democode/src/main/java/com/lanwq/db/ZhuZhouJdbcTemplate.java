package com.lanwq.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.List;

/**
 * @ClassName ZhuZhouJdbcTemplate
 * @Description TODO
 * @Author lanwenquan
 * @Date 2020/3/18 13:39
 */
public class ZhuZhouJdbcTemplate extends JdbcTemplate {
    private static final Logger logger = LoggerFactory.getLogger(ZhuZhouJdbcTemplate.class);

    private static  DataSource dataSource;

    private static ZhuZhouJdbcTemplate instance = null;

    private static final String USER_NAME = "dossier";

    private static final String PASSWORD = "dragon";

    private static final String JDBC_URL = "jdbc:oracle:thin:@192.168.44.90:1521:orcl";

    private static final String DRIVER_CLASS = "oracle.jdbc.driver.OracleDriver";

    static {
        logger.info("开始获取株洲门户数据库数据源。读取配置文件参数：{},{},{}", USER_NAME, PASSWORD, JDBC_URL);
        DriverManagerDataSource driverManagerDataSource;
        try {
            driverManagerDataSource = new DriverManagerDataSource(JDBC_URL, USER_NAME, PASSWORD);
            driverManagerDataSource.setDriverClassName(DRIVER_CLASS);
            logger.info("获取株洲门户数据库数据源成功。ur:【{}】, userName: 【{}】, password: 【{}】",
                    driverManagerDataSource.getUrl(), driverManagerDataSource.getUsername(), driverManagerDataSource.getPassword());
            dataSource = driverManagerDataSource;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ZhuZhouJdbcTemplate() {
        super(dataSource);
    }

    public static ZhuZhouJdbcTemplate getInstance(){
        if (instance == null) {
            synchronized(ZhuZhouJdbcTemplate.class) {
                if(instance == null)
                    instance = new ZhuZhouJdbcTemplate();
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        ZhuZhouJdbcTemplate jdbcTemplate = ZhuZhouJdbcTemplate.getInstance();
        System.out.println(jdbcTemplate);
        String sql = "select code from t_sys_function where code=?";
        try {
//            String s = jdbcTemplate.queryForObject(sql, String.class, "13");
            List<String> s = jdbcTemplate.queryForList(sql, String.class, "13");
            System.out.println(s);
            if(s.size()> 0) {
                System.out.println("存在。。。");
            }
        } catch (IncorrectResultSizeDataAccessException e) {
            e.printStackTrace();
            /*String sl = "insert into t_sys_function values(?,?)";
            int s2 = jdbcTemplate.update(sl, "13","13");
            System.out.println(s2);*/
        }
        /*jdbcTemplate.query(sql, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                System.out.println(rs.getString("CODE"));
                System.out.println(rs.getString("NAME"));
            }
        });
        ZhuZhouJdbcTemplate jdbcTemplate2 = ZhuZhouJdbcTemplate.getInstance();
        System.out.println(jdbcTemplate2);*/
    }
}