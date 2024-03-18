package com.bienesRaices.Services;

import com.bienesRaices.Domain.ImageProperty;
import com.bienesRaices.Domain.Property;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;

@Service
public interface ImagePropertyService {

    public ImageProperty getImageProperty(long id);
    public List<ImageProperty> getImagesProperty(long id);
    public void delete(long id);
    public void save(ImageProperty imageProperty);
}
