package com.ett.jasper;

import com.ett.jasper.po.City;
import com.ett.jasper.po.User;
import com.ett.jasper.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Vin lan
 * @className CityService
 * @description TODO
 * @createTime 2020-09-04  09:49
 **/
@Service(value = "cityService")
public class CityService {
    @Autowired
    private CityRepository cityRepository;

    public List<City> getAllCityInfo() {
        return cityRepository.getAllCityInfo();
    }

    public List<User> getAllUser() {
        return cityRepository.getAllUser();
    }
}
