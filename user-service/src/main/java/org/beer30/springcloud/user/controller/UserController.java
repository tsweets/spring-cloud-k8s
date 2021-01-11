package org.beer30.springcloud.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.beer30.springcloud.user.domain.User;
import org.beer30.springcloud.user.exception.UserNotFoundException;
import org.beer30.springcloud.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author tsweets
 * Date: 1/9/21 - 8:16 PM
 */

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    @Operation(summary = "Get User by ID")
    @ApiResponses(
            value =  {
                    @ApiResponse(responseCode = "200", description = "Found the User", content = {@Content(mediaType = "application/json")})
            }
    )
    @GetMapping(value = "/users/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUserById (@PathVariable Long id) {
        log.info("REST: Get User By ID: {}", id);
        User user = userService.findUserById(id).orElseThrow(() -> new UserNotFoundException(id));
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
}
