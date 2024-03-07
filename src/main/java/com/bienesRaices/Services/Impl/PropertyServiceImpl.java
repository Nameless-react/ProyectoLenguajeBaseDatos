package com.bienesRaices.Services.Impl;


import com.bienesRaices.Domain.Property;
import com.bienesRaices.Services.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.bienesRaices.Dao.PropertyDao;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PropertyServiceImpl implements PropertyService {
    @Autowired
    private PropertyDao propertyDao;

    @Override
    @Transactional(readOnly = true)
    public Property getProperty(long id) {
        return propertyDao.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Property> getProperties() {
        System.out.println(propertyDao.findAll());
        return propertyDao.findAll();
    }

    @Override
    @Transactional
    public void delete(long id) {
        propertyDao.deleteById(id);
    }

    @Override
    @Transactional
    public void save(Property property) {
        propertyDao.save(property);
    }

    @Override
    public Page<Property> getPropertyPage(SpringDataWebProperties.Pageable pageable) {
        return null;
    }

    @Override
    public Page<Property> getPropertyPageBetweenPrice(long initPrice, long finishPrice, Pageable pageable) {
        return null;
    }
}
