package springtest.tx;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Types;

/**
 * @author Vin lan
 * @className UserServiceImpl
 * @description
 * @createTime 2021-11-24  09:46
 **/
public class UserServiceImpl implements UserService {
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(User user) throws Exception {
        System.out.println("start add a user");
        System.out.println("added a user： " + user.toString());
        jdbcTemplate.update("insert into suser(ID, email, name) values (?, ?, ?)",
                new Object[] {user.getUserId(), user.getName(), user.getEmail()},
                new int[] {Types.INTEGER, Types.VARCHAR, Types.VARCHAR});
        System.out.println("end add a user");
//        事务测试，加上这句代码则数据不会保存到数据库里
        throw new RuntimeException("aa");
    }
}
