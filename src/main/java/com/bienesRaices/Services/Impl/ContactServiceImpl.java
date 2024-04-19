package com.bienesRaices.Services.Impl;

import com.bienesRaices.Dao.ContactDao;
import com.bienesRaices.Domain.Contact;
import com.bienesRaices.Services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService {
    @Autowired
    private ContactDao contactDao;

    @Override
    public void save(Contact contact) {
        contactDao.insertContact(contact.getProperty().getIdProperty(), contact.getName(), contact.getPhone(), contact.getEmail(), contact.getMessage());
    }
}
