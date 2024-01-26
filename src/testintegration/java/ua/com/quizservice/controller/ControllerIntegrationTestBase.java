package ua.com.quizservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import ua.com.quizservice.IntegrationTestBase;

/**
 * @author Artyom Kondratenko
 * @since 12/15/23
 */
@AutoConfigureMockMvc
public abstract class ControllerIntegrationTestBase extends IntegrationTestBase {

  @Autowired protected ObjectMapper objectMapper;

  @Autowired protected MockMvc mockMvc;
}
