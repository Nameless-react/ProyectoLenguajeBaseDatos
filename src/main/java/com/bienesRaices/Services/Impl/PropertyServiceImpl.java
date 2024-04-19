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
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;

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
        return propertyDao.findAll();
    }

    @Override
    @Transactional
    public void delete(long id) {
        propertyDao.deleteById(id);
    }

    @Override
    @Transactional
    public Property save(Property property) {
        return propertyDao.save(property);
    }

    @Override
    public List<Property> getPropertiesByAgent(Long id) {
        return propertyDao.findAll().stream().filter(property -> property.getAgent().getIdAgent() == id).collect(Collectors.toList());
    }

    @Override
    public Page<Property> getPropertyPage(SpringDataWebProperties.Pageable pageable) {
        return null;
    }

    @Override
    public Page<Property> getPropertyPageBetweenPrice(long initPrice, long finishPrice, Pageable pageable) {
        return null;
    }

    @Override
    public Page<Property> getProperties(String word, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        if (word != null && !word.isEmpty()) {
             return propertyDao.findByNameContainingIgnoreCase(word, pageRequest);
        }else{
               return propertyDao.findAll(pageRequest);
        }
    }
}
