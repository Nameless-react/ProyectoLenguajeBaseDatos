package com.bienesRaices.Services.Impl;

import com.bienesRaices.Domain.Address;
import com.bienesRaices.Services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import com.bienesRaices.Dao.AddressDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressDao addressDao;

    @Override
    @Transactional(readOnly = true)
    public Address getAddress(long id) {
        return addressDao.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Address> getAddresses() {
        return addressDao.findAll();
    }

    @Override
    @Transactional
    public void delete(long id) {
        addressDao.deleteById(id);
    }

    @Override
    @Transactional
    public Address save(Address address) {
        return addressDao.save(address);
    }
}
