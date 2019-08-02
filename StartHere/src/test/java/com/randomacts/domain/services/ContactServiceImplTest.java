package com.randomacts.domain.services;

import com.randomacts.domain.RandomActsApplication;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RandomActsApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ContactServiceImplTest
{

    @Before
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown()
    {
    }

    @org.junit.Test
    public void delete()
    {
    }

    @Test
    public void save()
    {
    }

    @Test
    public void update()
    {
    }
}