package org.main.service.integration_tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.main.service.dto.AuthenticationRequestDTO;
import org.main.service.entity.AccountStatus;
import org.main.service.entity.Role;
import org.main.service.entity.User;
import org.main.service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@PropertySource("classpath:application.properties")
@AutoConfigureMockMvc
public class AuthenticationWorkflowTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    UserRepository userRepository;

    @Test
    void testCorrectAuthenticationProcess() throws Exception {
        insertUserIntoDb();
        mockMvc.perform(
                        post("/auth")
                                .contentType("application/json")
                                .content(objectMapper.writeValueAsString(new AuthenticationRequestDTO("user@gmail.com", "user"))))
                .andExpect(status().isOk())
                .andExpect(result -> assertTrue(result.getResponse().getContentAsString().matches("\\{\"token\":\"\\S*\"}")));
    }

    @Test
    void testUserWithWrongPassword_Exception() throws Exception {
        mockMvc.perform(
                        post("/auth")
                                .contentType("application/json")
                                .content(objectMapper.writeValueAsString(new AuthenticationRequestDTO("user@gmail.com", "user_not"))))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertEquals("400 BAD_REQUEST \"Invalid credentials for user authentication. Please check for mistakes presence.\"",
                        result.getResolvedException().getMessage()));
    }

    @Test
    void testUserWithNotExistingEmail_Exception() throws Exception {
        mockMvc.perform(
                        post("/auth")
                                .contentType("application/json")
                                .content(objectMapper.writeValueAsString(new AuthenticationRequestDTO("anonymous@gmail.com", ""))))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertEquals("400 BAD_REQUEST \"Invalid credentials for user authentication. Please check for mistakes presence.\"",
                        result.getResolvedException().getMessage()));
    }

    private void insertUserIntoDb() {
        userRepository.save(
                User
                        .builder()
                        .firstName("test_user")
                        .lastName("last_name")
                        .nickname("test_user")
                        .login("user@gmail.com")
                        .password("$2a$12$u8evMp4zrebSaVPwifh5zuQo.A42QlPsz33tGp376ctzWrhR4sHOq")
                        .status(AccountStatus.ACTIVE)
                        .role(Role.USER)
                        .build()
        );
    }
}
