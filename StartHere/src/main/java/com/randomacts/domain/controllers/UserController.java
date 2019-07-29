package com.randomacts.domain.controllers;

import com.randomacts.domain.models.ErrorDetail;
import com.randomacts.domain.models.User;
import com.randomacts.domain.services.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController
{
    private static final Logger logger = LoggerFactory.getLogger(RolesController.class);

    @Autowired
    private UserService userService;



    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @ApiOperation(value = "Returns a list of all users. Requires admin role.", responseContainer = "List")
    @GetMapping(value = "/users",
                produces = {"application/json"})
    public ResponseEntity<?> listAllUsers(HttpServletRequest request)
    {
        logger.trace(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");

        List<User> myUsers = userService.findAll();
        return new ResponseEntity<>(myUsers, HttpStatus.OK);
    }


    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @ApiOperation(value = "Returns a single user based on user id. Requires Admin Role.", responseContainer = "User.class")
    @GetMapping(value = "/user/{userId}",
                produces = {"application/json"})
    public ResponseEntity<?> getUser(HttpServletRequest request,
                                     @PathVariable
                                             Long userId)
    {
        logger.trace(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");

        User u = userService.findUserById(userId);
        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    @ApiOperation(value = "Returns currently logged in user's username.")
    @GetMapping(value = "/getusername",
                produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<?> getCurrentUserName(HttpServletRequest request, Authentication authentication)
    {
        logger.trace(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");

        return new ResponseEntity<>(authentication.getPrincipal(), HttpStatus.OK);
    }


    @ApiOperation(value = "Creates a new user.", notes = "The newly created User id will be sent in the location header", response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User Created", response = void.class),
            @ApiResponse(code = 404, message = "Failed to create new user.", response = ErrorDetail.class)
    })
    @PostMapping(value = "/user",
                 consumes = {"application/json"},
                 produces = {"application/json"})
    public ResponseEntity<?> addNewUser(HttpServletRequest request, @Valid
    @RequestBody
            User newuser) throws URISyntaxException
    {
        logger.trace(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");

        newuser = userService.save(newuser);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{userid}").buildAndExpand(newuser.getUserid()).toUri();
        responseHeaders.setLocation(newUserURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @ApiOperation(value = "Updates user based on User ID.", notes = "The newly created User id will be sent in the location header", response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User Updated", response = void.class),
            @ApiResponse(code = 404, message = "Failed to update user.", response = ErrorDetail.class)
    })
    @PutMapping(value = "/user/{id}")
    public ResponseEntity<?> updateUser(HttpServletRequest request,
                                        @RequestBody
                                                User updateUser,
                                        @ApiParam(value = "id", required = true, example = "1")
                                        @PathVariable
                                                long id)
    {
        logger.trace(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");

        userService.update(updateUser, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @ApiOperation(value = "Deletes user based on user id.", response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User Deleted", response = void.class),
            @ApiResponse(code = 404, message = "Failed to delete user.", response = ErrorDetail.class)
    })
    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUserById(HttpServletRequest request,
                                            @ApiParam(value = "id", required = true, example = "1")
                                            @PathVariable
                                                    long id)
    {
        logger.trace(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");

        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}