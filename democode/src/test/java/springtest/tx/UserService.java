package springtest.tx;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Vin lan
 * @className UserService
 * @description 测试事务
 * @createTime 2021-11-24  09:45
 **/
@Transactional(propagation = Propagation.REQUIRED)
public interface UserService {
    void save(User user) throws Exception;
}
