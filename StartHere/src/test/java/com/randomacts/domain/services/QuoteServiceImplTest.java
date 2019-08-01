package com.randomacts.domain.services;

import com.randomacts.domain.RandomActsApplication;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RandomActsApplication.class)
class QuoteServiceImplTest
{

    @BeforeEach
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);
    }

    @AfterEach
    void tearDown()
    {
    }

    @Test
    void delete()
    {
    }

    @Test
    void save()
    {
    }

    @Test
    void update()
    {
    }
}