package org.openapitools;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.openapitools.api.RegisterApiController;
import org.openapitools.model.UserRegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(RegisterApiController.class)
class UserRegistrerAPITests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testRegisterUser() throws Exception {
        
        // Test success
        // Create the request
        UserRegistrationRequest request = new UserRegistrationRequest();
        request.setUsername("john_doe");
        request.setPassword("StrongPass#123");
        
        // IP from Canada
        request.setIpAddress("100.42.20.0");
        String requestJson = objectMapper.writeValueAsString(request);
        
        // Perform the POST request
        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Accept", MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Welcome 'john_doe', from 'Toronto'"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.uuid").exists());
   
    }

    @Test
    void testWeakPassword() throws Exception {
        
        // Create the request
        UserRegistrationRequest request = new UserRegistrationRequest();
        request.setUsername("john_doe");
        request.setPassword("J0hn");
        
        // IP from Canada
        request.setIpAddress("100.42.20.0");
        String requestJson = objectMapper.writeValueAsString(request);
        
        // Perform the POST request
        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Accept", MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message",Matchers.containsString("Error in field 'password': Need to be greater than 8 characters")))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
   

        request.setPassword("john_doe_john_doe");
        requestJson = objectMapper.writeValueAsString(request);
        
        // Perform the POST request
        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Accept", MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message",Matchers.containsString("Error in field 'password': Should contain at least 1 number, 1 Captialized letter and 1 special character in this set '_ # $ % .'")))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
        
        request.setPassword(null);
        requestJson = objectMapper.writeValueAsString(request);
        
        // Perform the POST request
        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Accept", MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message",Matchers.containsString("Error in field 'password': field is required")))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
        
    }


    @Test
    void testRequiredFileds() throws Exception {
        // Create the request
        UserRegistrationRequest request = new UserRegistrationRequest();
        request.setUsername(null);
        request.setPassword("StrongPass#123");
        
        // IP from Canada
        request.setIpAddress("100.42.20.0");
        String requestJson = objectMapper.writeValueAsString(request);
        
        // Perform the POST request
        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Accept", MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message",Matchers.containsString("Error in field 'username': field is required")))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        request.setUsername("");

        // Perform the POST request
        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Accept", MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message",Matchers.containsString("Error in field 'username': field is required")))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());



        request.setUsername("john_doe");
        request.setPassword(null);
        
        requestJson = objectMapper.writeValueAsString(request);
        
        // Perform the POST request
        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Accept", MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message",Matchers.containsString("Error in field 'password': field is required")))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());    

        request.setPassword("");

        // Perform the POST request
        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Accept", MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message",Matchers.containsString("Error in field 'password': field is required")))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());            
        
        request.setUsername("john_doe");
        request.setPassword("StrongPass#123");
        request.setIpAddress(null);
        
        requestJson = objectMapper.writeValueAsString(request);
        
        // Perform the POST request
        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Accept", MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message",Matchers.containsString("Error in field 'ipAddress': field is required")))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());       
        
        request.setIpAddress("");        
        // Perform the POST request
        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Accept", MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message",Matchers.containsString("Error in field 'ipAddress': field is required")))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());   
    }

    @Test
    void testIpFormat() throws Exception {
        
        // Create the request
        UserRegistrationRequest request = new UserRegistrationRequest();
        request.setUsername("john_doe");
        request.setPassword("StrongPass#123");
        
        // IP from Canada
        request.setIpAddress("123.12.100.42.20.0");
        String requestJson = objectMapper.writeValueAsString(request);
        
        // Perform the POST request
        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Accept", MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message",Matchers.containsString("Error in field 'ipAddress': the format of the IP must be valid")))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
   
                
    }

    @Test
    void testInvalidCountry() throws Exception {
        
        // Create the request
        UserRegistrationRequest request = new UserRegistrationRequest();
        request.setUsername("john_doe");
        request.setPassword("StrongPass#123");
        
        // IP from Canada
        request.setIpAddress("100.42.19.0");
        String requestJson = objectMapper.writeValueAsString(request);
        
        // Perform the POST request
        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Accept", MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message",Matchers.containsString("Only fellows from Canada may use the system. Your country seems to be United States")))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
   
                
    }

}