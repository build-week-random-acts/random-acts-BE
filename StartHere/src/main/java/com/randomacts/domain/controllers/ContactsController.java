package com.randomacts.domain.controllers;


import com.randomacts.domain.models.Contacts;
import com.randomacts.domain.models.ErrorDetail;
import com.randomacts.domain.services.ContactService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/contacts")
public class ContactsController
{
    private static final Logger logger = LoggerFactory.getLogger(RolesController.class);


    @Autowired
    private ContactService contactService;

    @ApiOperation(value = "Updates contact information based on contact ID.", notes = "Updates contact info based on contact ID", response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Contact Updated", response = void.class),
            @ApiResponse(code = 404, message = "Failed to update contact.", response = ErrorDetail.class)
    })
    @PutMapping(value = "/contact/{id}")
    public ResponseEntity<?> updateContact(HttpServletRequest request,
                                           @RequestBody Contacts updateContact,
                                           @PathVariable long id)

    {
        logger.trace(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");
        contactService.update(updateContact, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
