package com.randomacts.domain.services;

import com.randomacts.domain.RandomActsApplication;
import com.randomacts.domain.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RandomActsApplication.class)
public class UserServiceImplTest
{
    @Autowired
    UserService userService;

    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findAll()
    {
        assertEquals(5, userService.findAll().size());
    }

}
