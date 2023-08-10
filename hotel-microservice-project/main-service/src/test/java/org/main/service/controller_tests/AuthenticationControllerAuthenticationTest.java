package org.main.service.controller_tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.main.service.configuration.JwtFilter;
import org.main.service.controller.AuthenticationController;
import org.main.service.dto.AuthenticationRequestDTO;
import org.main.service.dto.TokenDTO;
import org.main.service.exceptions.AuthenticationException;
import org.main.service.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.doReturn;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;




@WebMvcTest(
        value = AuthenticationController.class,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = JwtFilter.class),
        excludeAutoConfiguration = SecurityAutoConfiguration.class
)
public class AuthenticationControllerAuthenticationTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    AuthenticationService authenticationService;

    AuthenticationRequestDTO requestDTO = new AuthenticationRequestDTO("test_mail@gmail.com", "testPassword");;
    TokenDTO tokenDTO = new TokenDTO("validToken");


    @Test
    void successfullyAuthenticationTest() throws Exception {
        doReturn(tokenDTO.getToken()).when(authenticationService).authenticateUser(requestDTO);

        this.mockMvc.perform(
                post("/auth")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO))
        )
                .andExpect(status().isOk())
                .andExpect(result -> assertEquals(MediaType.APPLICATION_JSON.toString(), result.getResponse().getContentType()))
                .andExpect(result -> assertEquals(String.format("{\"token\":\"%s\"}", tokenDTO.getToken()), result.getResponse().getContentAsString()));
    }

    @Test
    void authenticationExceptionThrowing() throws Exception {
        doThrow(AuthenticationException.class).when(authenticationService).authenticateUser(requestDTO);

        this.mockMvc.perform(
                post("/auth")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO))
        )
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResponseStatusException))
                .andExpect(result -> assertEquals("400 BAD_REQUEST", result.getResolvedException().getMessage()));
    }

}
