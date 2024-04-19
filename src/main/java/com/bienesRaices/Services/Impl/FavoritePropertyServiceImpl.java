package com.bienesRaices.Services.Impl;


import com.bienesRaices.Dao.FavoritePropertyDao;
import com.bienesRaices.Domain.FavoriteProperty;
import com.bienesRaices.Services.FavoritePropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavoritePropertyServiceImpl implements FavoritePropertyService {

    @Autowired
    private FavoritePropertyDao favoritePropertyDao;


    @Override
    @Transactional
    public void delete(Long id) {
        favoritePropertyDao.deleteFavoriteProperty(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FavoriteProperty> getFavoriteProperties(Long id) {
        return favoritePropertyDao.findAll().stream().filter(favoriteProperty -> favoriteProperty.getUser().getIdUser() == id).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void save(Long idProperty, Long idUser) {
        favoritePropertyDao.addFavoriteProperty(idProperty, idUser);
    }
}
