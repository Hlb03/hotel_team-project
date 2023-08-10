package org.main.service.controller_tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.main.service.configuration.JwtFilter;
import org.main.service.controller.AuthenticationController;
import org.main.service.dto.MailRequestDTO;
import org.main.service.dto.RegistrationRequestDTO;
import org.main.service.exceptions.IncorrectPasswordsException;
import org.main.service.exceptions.LoginAlreadyRegisteredException;
import org.main.service.service.implementations.AuthenticationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(
        value = AuthenticationController.class,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = JwtFilter.class),
        excludeAutoConfiguration = SecurityAutoConfiguration.class
)
public class AuthenticationControllerRegistrationTest {


    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    AuthenticationServiceImpl authenticationService;
    @MockBean
    RestTemplate restTemplate;

    RegistrationRequestDTO requestDTO;

    @BeforeEach
    void configRequestDTO() {
        requestDTO = new RegistrationRequestDTO("test_name", "test_surname", "test_nickname",
                "test@gmail.com", 30, "+380677670646",
                "test_password", "test_password");
    }

    @Test
    void successfullyAddNewUser() throws Exception {
        ReflectionTestUtils.setField(authenticationService, "restTemplate", restTemplate);

        ResponseEntity<String> response = new ResponseEntity<>("Message was sent", HttpStatus.OK);

        doReturn(response).when(restTemplate).postForEntity("http://MAILING/hotel-rent/mail/activate/{activationCode}",
                new MailRequestDTO(requestDTO.getSurname(), requestDTO.getMail()),
                String.class,
                "activationCode");

        doNothing().when(authenticationService).registerUser(requestDTO);

        this.mockMvc.perform(
                        post("/registration")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(requestDTO))
                )
                .andExpect(status().isCreated())
                .andExpect(result -> assertEquals(HttpStatus.OK, response.getStatusCode()))
                .andExpect(result -> assertEquals("Message was sent", response.getBody()));
    }

    @Test
    void registerUserWithDuplicateLogin_ExceptionThrowing() throws Exception {
        doThrow(LoginAlreadyRegisteredException.class).when(authenticationService)
                .registerUser(requestDTO);

        this.mockMvc.perform(
                        post("/registration")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(requestDTO))
                )
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResponseStatusException))
                .andExpect(result -> assertEquals("400 BAD_REQUEST", result.getResolvedException().getMessage()));
    }

    @Test
    void registerUserWithWrongPasswords_ExceptionThrowing() throws Exception {
        doThrow(IncorrectPasswordsException.class).when(authenticationService)
                .registerUser(requestDTO);

        this.mockMvc.perform(
                post("/registration")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO))
        )
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResponseStatusException))
                .andExpect(result -> assertEquals("400 BAD_REQUEST", result.getResolvedException().getMessage()));
    }
}
