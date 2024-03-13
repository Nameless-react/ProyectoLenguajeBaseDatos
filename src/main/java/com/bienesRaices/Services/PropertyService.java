package com.bienesRaices.Services;

import com.bienesRaices.Domain.Property;
import org.springframework.data.domain.Page;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PropertyService {
    public Property getProperty(long id);
    public List<Property> getProperties();
    public void delete(long id);
    public Property save(Property property);
    Page<Property> getPropertyPage(SpringDataWebProperties.Pageable pageable);
    Page<Property> getPropertyPageBetweenPrice(long initPrice, long finishPrice, Pageable pageable);
}
