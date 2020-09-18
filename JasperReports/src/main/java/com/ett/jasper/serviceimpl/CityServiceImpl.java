package com.ett.jasper.serviceimpl;

import com.ett.jasper.po.City;
import com.ett.jasper.repository.CityRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Vin lan
 * @className CityService
 * @description TODO
 * @createTime 2020-09-04  09:49
 **/
@Service
public class CityServiceImpl {

    @Autowired
    private CityRepository cityRepository;

    @Transactional
    public List<City> getAllCityInfo() {
        return cityRepository.getAllCityInfo();
    }
}
