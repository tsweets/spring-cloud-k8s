package org.beer30.springcloud.user;

import org.apache.commons.lang3.RandomStringUtils;
import org.beer30.springcloud.user.controller.UserController;
import org.beer30.springcloud.user.domain.User;
import org.beer30.springcloud.user.repository.UserRepository;
import org.beer30.springcloud.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Optional;

import static org.beer30.springcloud.user.UserTestUtils.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author tsweets
 * Date: 1/9/21 - 8:29 PM
 */

@WebMvcTest(UserController.class)
//@SpringBootTest(classes = UserServiceApplication.class)
public class UserControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    private User user;


    @BeforeEach
    public void setup(){
        user = new User();
        user.setId(3l);
        user.setLogin(DEFAULT_LOGIN);
        user.setPassword(RandomStringUtils.randomAlphanumeric(60));
        user.setActivated(true);
        user.setEmail(DEFAULT_EMAIL);
        user.setFirstName(DEFAULT_FIRSTNAME);
        user.setLastName(DEFAULT_LASTNAME);
    }

    @Test
    public void getCardholder() throws Exception {

        given(userService.findUserById(user.getId())).willReturn(Optional.of(user));

        MvcResult result = mockMvc.perform(get("/api/v1/users/id/{id}", user.getId()).accept(MediaType.APPLICATION_JSON))

                .andDo(print())
                .andExpect(status().isOk())
                //            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                //          .andExpect(jsonPath("$.firstName").value("Oliver"))
                .andReturn();
    }
}
