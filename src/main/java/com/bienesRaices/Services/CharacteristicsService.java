package com.bienesRaices.Services;

import com.bienesRaices.Domain.Address;
import com.bienesRaices.Domain.Characteristics;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CharacteristicsService {
    public Characteristics getCharacteristic(long id);
    public List<Characteristics> getCharacteristics();
    public void delete(long id);
    public Characteristics save(Characteristics characteristics);
}
