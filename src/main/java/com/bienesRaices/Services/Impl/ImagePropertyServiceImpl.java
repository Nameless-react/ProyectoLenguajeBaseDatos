package com.bienesRaices.Services.Impl;

import com.bienesRaices.Dao.ImagePropertyDao;
import com.bienesRaices.Domain.ImageProperty;
import com.bienesRaices.Services.ImagePropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ImagePropertyServiceImpl implements ImagePropertyService {

    @Autowired
    private ImagePropertyDao imagePropertyDao;

    @Override
    @Transactional(readOnly = true)
    public ImageProperty getImageProperty(long id) {
        return imagePropertyDao.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ImageProperty> getProperties() {
        return imagePropertyDao.findAll();
    }

    @Override
    @Transactional
    public void delete(long id) {
        imagePropertyDao.deleteById(id);
    }

    @Override
    @Transactional
    public void save(ImageProperty imageProperty) {
        imagePropertyDao.save(imageProperty);
    }
}
