package com.bienesRaices.Services;

import com.bienesRaices.Domain.Address;
import com.bienesRaices.Domain.Property;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AddressService {
    public Address getAddress(long id);
    public List<Address> getAddresses();
    public void delete(long id);
    public Address save(Address address);
}
