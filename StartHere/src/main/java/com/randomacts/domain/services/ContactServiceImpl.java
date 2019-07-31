package com.randomacts.domain.services;

import com.randomacts.domain.exceptions.ResourceNotFoundException;
import com.randomacts.domain.models.Contacts;
import com.randomacts.domain.repository.ContactsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Security;
import java.util.ArrayList;
import java.util.List;

@Service(value = "contactService")
public class ContactServiceImpl implements ContactService
{

    @Autowired
    private ContactsRepository contactsrepo;

    @Override
    public List<Contacts> findAll()
    {
        List<Contacts> list = new ArrayList<>();
        contactsrepo.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Contacts findContactById(long id)
    {
        return contactsrepo.findById(id).orElseThrow(() -> new ResourceNotFoundException(Long.toString(id)));
    }

    @Override
    public void delete(long id)
    {
       if (contactsrepo.findById(id).isPresent())
       {
           contactsrepo.deleteById(id);
       }else
       {
           throw new ResourceNotFoundException(Long.toString(id));
       }
    }

    @Transactional
    @Override
    public Contacts save(Contacts contacts)
    {

        Contacts newContact = new Contacts();

        newContact.setFname(contacts.getFname());
        newContact.setLname(contacts.getLname());
        newContact.setEmail(contacts.getEmail());
        newContact.setPhone(contacts.getPhone());

        return contactsrepo.save(newContact);
    }

    @Override
    public List<Contacts> findByUserName(String username)
    {
        List<Contacts> list = new ArrayList<>();
        contactsrepo.findAll().iterator().forEachRemaining(list::add);

        list.removeIf(c -> !c.getUser().getUsername().equalsIgnoreCase(username));
        return list;
    }

    @Override
    public Contacts update(Contacts contacts, long id)
    {
        Contacts currentContact = contactsrepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Long.toString(id)));

        if (contacts.getFname() != null)
        {
            currentContact.setFname(contacts.getFname());
        }

        if (contacts.getLname() != null)
        {
            currentContact.setLname(contacts.getLname());
        }

        if (contacts.getEmail() != null)
        {
            currentContact.setEmail(contacts.getEmail());
        }

        if (contacts.getPhone() != null)
        {
            currentContact.setPhone(contacts.getPhone());
        }


        return contactsrepo.save(currentContact);
    }
}
