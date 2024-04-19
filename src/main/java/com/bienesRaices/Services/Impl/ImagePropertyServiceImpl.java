package com.bienesRaices.Services.Impl;

import com.bienesRaices.Dao.ImagePropertyDao;
import com.bienesRaices.Domain.ImageProperty;
import com.bienesRaices.Services.ImagePropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<ImageProperty> getImagesProperty(long id) {
        return imagePropertyDao.findAll().stream().filter(image -> image.getIdProperty() == id).toList();
    }

    @Override
    @Transactional
    public void delete(long id) {
        imagePropertyDao.deleteImageProperty(id);
    }

    @Override
    @Transactional
    public void save(Long idProperty, String image) {
        imagePropertyDao.addPropertyImage(idProperty, image);
    }
}
