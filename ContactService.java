package com.example.scm.services;

import java.util.List;

import org.springframework.data.domain.Page;
import com.example.scm.entities.Contacts;
import com.example.scm.entities.User;

public interface ContactService {
    // save contact
    Contacts save(Contacts contact);

    // update Contact
    Contacts updateContact(Contacts contact);

    //get Contacts
    List<Contacts> getAll();

    //get Contact by id
    Contacts getById(String id);

    // delete contact
    void delete(String id);

    // search Contact
    Page<Contacts> searchByName(String nameKeyword, int size, int page, String sortBy, String order, User user);
    
    Page<Contacts> searchByEmail(String emailKeyword, int size, int page, String sortBy, String order, User user);

    Page<Contacts> searchByPhoneNumber(String phoneNumberKeyword, int size, int page, String sortBy, String order, User user);

    // get contact by user id
    List<Contacts> getByUserId(String userId);

    Page<Contacts> getByUser(User user, int page, int size, String sortField, String sortDirection);
}
