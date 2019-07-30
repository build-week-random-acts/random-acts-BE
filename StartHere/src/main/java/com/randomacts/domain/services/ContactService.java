package com.randomacts.domain.services;

import com.randomacts.domain.models.Contacts;

import java.util.List;

public interface ContactService
{
    List<Contacts> findAll();

    Contacts findContactById(long id);

    List<Contacts> findByUserName(String username);

    void delete(long id);

    Contacts save(Contacts contacts);

}
