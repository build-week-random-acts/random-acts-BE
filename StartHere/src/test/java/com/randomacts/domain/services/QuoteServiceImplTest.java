package com.randomacts.domain.services;

import com.randomacts.domain.RandomActsApplication;
import com.randomacts.domain.config.SecurityConfig;
import com.randomacts.domain.exceptions.ResourceNotFoundException;
import com.randomacts.domain.models.Quote;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityNotFoundException;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RandomActsApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class QuoteServiceImplTest
{

    @Autowired
    private QuoteService quoteService;

//    private MockMvc mockMvc;

//    @Bean
//    public Authentication authentication() {
//        Collection<GrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
//        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken("customUsername", "customPassword", authorities); return authentication; }
//
//    @Autowired
//    private Authentication authentication;

    @Before
    public void AsetUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void BtearDown() throws Exception
    {
    }

    @Test
    public void Csave()
    {
        Quote q2 = new Quote("Save this quote");

        Quote saveQuote = quoteService.save(q2);

        assertNotNull(saveQuote);

        Quote foundQuote = quoteService.findQuoteById(saveQuote.getQuotesid());
        assertEquals(saveQuote.getQuote(), foundQuote.getQuote());
    }

    @Test
    public void Dupdate()
    {
        Quote q1 = new Quote("a new quote");
        q1.setQuotesid(80);

        Quote updatedQuote = quoteService.update(q1, 6);

        assertEquals("a new quote", updatedQuote.getQuote());
    }

    @Test (expected = ResourceNotFoundException.class)
    public void EdeleteNotFound()
    {
        quoteService.delete(3000);
        assertEquals(30, quoteService.findAll().size());
    }

//    @WithMockUser(roles = "ADMIN")
    @Test
    public void FdeleteFound()
    {
        quoteService.delete(6);
        assertEquals(29, quoteService.findAll().size());
    }
}