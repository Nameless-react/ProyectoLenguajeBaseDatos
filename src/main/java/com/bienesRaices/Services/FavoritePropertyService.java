package com.bienesRaices.Services;

import com.bienesRaices.Domain.FavoriteProperty;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FavoritePropertyService {
    public void delete(Long id);
    public List<FavoriteProperty> getFavoriteProperties(Long id);

    public void save(Long idProperty, Long idUser);
}
