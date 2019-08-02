package com.randomacts.domain;

import com.randomacts.domain.models.*;
import com.randomacts.domain.services.ContactService;
import com.randomacts.domain.services.QuoteService;
import com.randomacts.domain.services.RoleService;
import com.randomacts.domain.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Transactional
@Component
public class SeedData implements CommandLineRunner
{
    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @Autowired
    QuoteService quoteService;

    @Autowired
    ContactService contactService;


    @Override
    public void run(String[] args) throws Exception
    {
        Role r1 = new Role("admin");
        Role r2 = new Role("user");
        Role r3 = new Role("data");

        roleService.save(r1);
        roleService.save(r2);
        roleService.save(r3);

        // admin, data, user
        ArrayList<UserRoles> admins = new ArrayList<>();
        admins.add(new UserRoles(new User(), r1));
        admins.add(new UserRoles(new User(), r2));
        admins.add(new UserRoles(new User(), r3));
        User u1 = new User("admin", "password", admins);
        u1.getQuotes().add(new Quote("A creative man is motivated by the desire to achieve, not by the desire to beat others", u1));
        u1.getQuotes().add(new Quote("The question isn't who is going to let me; it's who is going to stop me.", u1));
        u1.getContacts().add(new Contacts("Beth", "Fonzarelli", "bethf@gmail.com", "999-999-9999", u1));
        userService.save(u1);

        // data, user
        ArrayList<UserRoles> datas = new ArrayList<>();
        datas.add(new UserRoles(new User(), r3));
        datas.add(new UserRoles(new User(), r2));
        User u2 = new User("cinnamon", "1234567", datas);
        u1.getContacts().add(new Contacts("Jude", "Mackenraw", "judem@gmail.com", "999-999-9999", u2));
        userService.save(u2);

        // user
        ArrayList<UserRoles> users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));
        User u3 = new User("barnbarn", "ILuvM4th!", users);
        u3.getQuotes().add(new Quote("Live long and prosper", u3));
        u3.getQuotes().add(new Quote("The enemy of my enemy is the enemy I kill last", u3));
        u3.getQuotes().add(new Quote("Beam me up", u3));
        userService.save(u3);

        users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));
        User u4 = new User("Bob", "password", users);
        userService.save(u4);

        users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));
        User u5 = new User("Jane", "password", users);
        userService.save(u5);

//        // Quotes


        Quote q1 = new Quote("Tweet or Facebook message a genuine compliment to three people right now.");
        Quote q2 = new Quote("While you're out, compliment a parent on how well-behaved their child is.");
        Quote q3 = new Quote("Don't write the angry internet comment you're thinking of writing.");
        Quote q4 = new Quote("When everyone around you is gossiping about someone, be the one to butt in with something nice.");
        Quote q5 = new Quote("Cook a meal or do a load of laundry for a friend who just had a baby or is going through a difficult time.");
        Quote q6 = new Quote("If you walk by a car with an expired parking meter, put a quarter in it.");
        Quote q7 = new Quote("Put your phone away.");
        Quote q8 = new Quote("Hang out with the person who just moved to town.");
        Quote q9 = new Quote("Offer a homeless person your leftovers bag from the restaurant.");
        Quote q10 = new Quote("Each time you get a new piece of clothing, donate an old one.");
        Quote q11 = new Quote("Don't interrupt when someone else is speaking.");
        Quote q12 = new Quote("Email or write an old teacher who made a difference in your life.");
        Quote q13 = new Quote("Compliment someone to their boss.");
        Quote q14 = new Quote("Leave a nice server the biggest tip you can afford.");
        Quote q15 = new Quote("Smile at someone on the street, just because.");
        Quote q16 = new Quote("Let someone into your lane. They're probably in a rush just like you.");
        Quote q17 = new Quote("Forgive someone, and never bring up the issue again.");
        Quote q18 = new Quote("Talk to the shy person who's sitting by themselves at a party.");
        Quote q19 = new Quote("Leave your New York Times or Us Weekly behind for someone else to read at the coffeeshop, the doctor's office, or on a plane.");
        Quote q20 = new Quote("Cut someone some slack.");
        Quote q21 = new Quote("Help a mother with her baby stroller.");
        Quote q22 = new Quote("Become a big brother or big sister.");
        Quote q23 = new Quote("Let the person behind you at the supermarket checkout with one or two items go ahead of you.");
        Quote q24 = new Quote("Write someone a letter. Like a real letter, on paper. And mail it!");
        Quote q25 = new Quote("Give away stuff for free on Craigslist.");


        quoteService.save(q1);
        quoteService.save(q2);
        quoteService.save(q3);
        quoteService.save(q4);
        quoteService.save(q5);
        quoteService.save(q6);
        quoteService.save(q7);
        quoteService.save(q8);
        quoteService.save(q9);
        quoteService.save(q10);
        quoteService.save(q11);
        quoteService.save(q12);
        quoteService.save(q13);
        quoteService.save(q14);
        quoteService.save(q15);
        quoteService.save(q16);
        quoteService.save(q17);
        quoteService.save(q18);
        quoteService.save(q19);
        quoteService.save(q20);
        quoteService.save(q21);
        quoteService.save(q22);
        quoteService.save(q23);
        quoteService.save(q24);
        quoteService.save(q25);


        // Contacts
        users.add(new UserRoles(new User(), r2));
//        Contacts c1 = new Contacts("Beth", "Fonzarelli", "bethf@gmail.com", "999-999-9999");
//        Contacts c2 = new Contacts("Jude", "Mackenraw", "judem@gmail.com", "999-999-9999");
//
//        contactService.save(c1);
//        contactService.save(c2);

    }
}