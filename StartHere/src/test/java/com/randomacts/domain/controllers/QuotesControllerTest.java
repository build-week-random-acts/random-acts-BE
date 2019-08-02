package com.randomacts.domain.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.randomacts.domain.models.*;
import com.randomacts.domain.services.QuoteService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = QuotesController.class, secure = false)
public class QuotesControllerTest
{
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuoteService quoteService;

    private List<Quote> quoteList;
    @Before
    public void setUp() throws Exception
    {
        quoteList = new ArrayList<>();

        Quote q1 = new Quote("Tweet or Facebook message a genuine compliment to three people right now.");
        Quote q2 = new Quote("While you're out, compliment a parent on how well-behaved their child is.");
        Quote q3 = new Quote("Don't write the angry internet comment you're thinking of writing.");
        Quote q4 = new Quote("When everyone around you is gossiping about someone, be the one to butt in with something nice.");
        Quote q5 = new Quote("Cook a meal or do a load of laundry for a friend who just had a baby or is going through a difficult time.");

        q1.setQuotesid(1);
        quoteList.add(q1);

        q2.setQuotesid(2);
        quoteList.add(q2);

        q3.setQuotesid(3);
        quoteList.add(q3);

        q4.setQuotesid(4);
        quoteList.add(q4);

        q5.setQuotesid(5);
        quoteList.add(q5);


    }

    @After
    public void tearDown() throws Exception
    {
    }

    @Test
    public void listAllQuotes() throws Exception
    {
        String apiUrl ="/quotes/quotes";

        Mockito.when(quoteService.findAll()).thenReturn(quoteList);

        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);
        MvcResult r = mockMvc.perform(rb).andReturn();
        String tr = r.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        String er = mapper.writeValueAsString(quoteList);

        assertEquals(er, tr);
    }

    @Test
    public void updateQuote1() throws Exception
    {
        String apiUrl = "/quotes/update/{id}";

        Quote q2 = new Quote("Test me!");
        q2.setQuotesid(10);

        Mockito.when(quoteService.update(q2, 10)).thenReturn(q2);

        ObjectMapper mapper = new ObjectMapper();
        String quoteString = mapper.writeValueAsString(q2);

        RequestBuilder rb = MockMvcRequestBuilders.put(apiUrl, 10)
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                .content(quoteString);

        mockMvc.perform(rb).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deleteQuoteById1() throws Exception
    {
        String apiUrl = "/quotes/quote/{id}";

        RequestBuilder rb = MockMvcRequestBuilders.delete(apiUrl, "1").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(rb).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }
}