package com.randomacts.domain.controllers;

import com.randomacts.domain.models.ErrorDetail;
import com.randomacts.domain.models.Quote;
import com.randomacts.domain.services.QuoteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/quotes")
public class QuotesController
{
    private static final Logger logger = LoggerFactory.getLogger(RolesController.class);

    @Autowired
    QuoteService quoteService;

    @GetMapping(value = "/quotes",
                produces = {"application/json"})
    public ResponseEntity<?> listAllQuotes(HttpServletRequest request)
    {
        logger.trace(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");

        List<Quote> allQuotes = quoteService.findAll();
        return new ResponseEntity<>(allQuotes, HttpStatus.OK);
    }


    @GetMapping(value = "/quote/{quoteId}",
                produces = {"application/json"})
    public ResponseEntity<?> getQuote(HttpServletRequest request,
                                      @PathVariable
                                              Long quoteId)
    {
        logger.trace(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");

        Quote q = quoteService.findQuoteById(quoteId);
        return new ResponseEntity<>(q, HttpStatus.OK);
    }


    @GetMapping(value = "/username/{userName}",
                produces = {"application/json"})
    public ResponseEntity<?> findQuoteByUserName(HttpServletRequest request,
                                                 @PathVariable
                                                         String userName)
    {
        logger.trace(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");

        List<Quote> theQuotes = quoteService.findByUserName(userName);
        return new ResponseEntity<>(theQuotes, HttpStatus.OK);
    }


    @PostMapping(value = "/quote")
    public ResponseEntity<?> addNewQuote(HttpServletRequest request, @Valid
    @RequestBody
            Quote newQuote) throws URISyntaxException
    {
        logger.trace(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");

        newQuote = quoteService.save(newQuote);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newQuoteURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{quoteid}").buildAndExpand(newQuote.getQuotesid()).toUri();
        responseHeaders.setLocation(newQuoteURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }


    @ApiOperation(value = "Updates quote based on quote ID.", notes = "Updates quote info based on quote ID", response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Quote Updated", response = void.class),
            @ApiResponse(code = 404, message = "Failed to update quote.", response = ErrorDetail.class)
    })
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> updateQuote(HttpServletRequest request,
                                         @RequestBody Quote updatedQuote,
                                         @PathVariable long id)
    {
        logger.trace(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");

        quoteService.update(updatedQuote, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping("/quote/{id}")
    public ResponseEntity<?> deleteQuoteById(HttpServletRequest request,
                                             @PathVariable
                                                     long id)
    {
        logger.trace(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");

        quoteService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
