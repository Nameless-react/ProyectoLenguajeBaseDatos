package com.bienesRaices.Services;

import com.bienesRaices.Domain.Contact;
import org.springframework.stereotype.Service;

@Service
public interface ContactService {
    public void save(Contact contact);
}
