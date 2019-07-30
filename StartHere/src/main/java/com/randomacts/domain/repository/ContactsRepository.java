package com.randomacts.domain.repository;

import com.randomacts.domain.models.Contacts;
import org.springframework.data.repository.CrudRepository;

public interface ContactsRepository extends CrudRepository<Contacts, Long>
{
}
