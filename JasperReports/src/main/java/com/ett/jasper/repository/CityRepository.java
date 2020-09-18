package com.ett.jasper.repository;

import com.ett.jasper.po.City;
import com.ett.jasper.po.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Repository
public class CityRepository {

    @Resource
    private SessionFactory sessionFactory;

    public List<City> getAllCityInfo() {
        Session session = sessionFactory.openSession();
        List<City> city = session.createQuery("from City").list();
        return city;
    }

    public List<User> getAllUser() {
        Session session = sessionFactory.openSession();
        return session.createQuery(" from User").list();
    }

    public List getUserListsByGroup(Map<String, Object> parameters) {
        String sql = "SELECT r.role_name, r.role_desc, u.user_id, \n" +
                " CONCAT(u.first_name, ' ', u.last_name) as user_name, u.last_update_time  FROM user u, role r \n" +
                " WHERE u.role_id=r.role_id AND r.role_name >= :fromScope and r.role_name <= :toScope " +
                "order by u.user_id;";
        Session session = sessionFactory.openSession();
        List list = session.createNativeQuery(sql).setParameter("fromScope", parameters.get("fromScope")).
                setParameter("toScope", parameters.get("toScope")).
                getResultList();
        session.close();
        return list;
    }
}
