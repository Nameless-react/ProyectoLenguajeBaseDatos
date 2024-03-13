package com.bienesRaices.Services;

import com.bienesRaices.Domain.ImageProperty;
import com.bienesRaices.Domain.Property;

import java.awt.*;
import java.util.List;

public interface ImagePropertyService {

    public ImageProperty getImageProperty(long id);
    public List<ImageProperty> getProperties();
    public void delete(long id);
    public void save(ImageProperty imageProperty);
}
