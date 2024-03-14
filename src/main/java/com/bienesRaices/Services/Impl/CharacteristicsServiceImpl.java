package com.bienesRaices.Services.Impl;

import com.bienesRaices.Dao.CharacteristicsDao;
import com.bienesRaices.Domain.Characteristics;
import com.bienesRaices.Services.CharacteristicsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CharacteristicsServiceImpl implements CharacteristicsService {
    @Autowired
    private CharacteristicsDao characteristicsDao;

    @Override
    @Transactional(readOnly = true)
    public Characteristics getCharacteristic(long id) {
        return characteristicsDao.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Characteristics> getCharacteristics() {
        return characteristicsDao.findAll();
    }

    @Override
    @Transactional
    public void delete(long id) {
        characteristicsDao.deleteById(id);
    }

    @Override
    @Transactional
    public Characteristics save(Characteristics characteristics) {
        return characteristicsDao.save(characteristics);
    }
}
