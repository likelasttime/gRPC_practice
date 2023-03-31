package com.example.demo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MembershipController.class)
@ExtendWith(MockitoExtension.class)
public class MembershipControllerTest {

    @InjectMocks
    private MembershipController membershipController;

    @MockBean
    private MembershipClient membershipClient;

    @Autowired
    private MockMvc mockMvc;

    static String username;

    static String profileImageUrl;

    @BeforeAll
    public static void beforeAll(){
        username = "user";
        profileImageUrl = "user.jpg";
    }

    @Test
    public void testGetProfile() throws Exception{

        Map<String, String> result = new HashMap<String, String>();
        result.put("username", username);
        result.put("profileImageUrl", profileImageUrl);

        when(membershipClient.findProfile(anyLong()))
                .thenReturn(result);

        mockMvc.perform(get("/profile/1"))
                .andDo(print())
                .andExpectAll(status().isOk(),
                        jsonPath("$.username").value(username),
                        jsonPath("$.profileImageUrl").value(profileImageUrl));
    }

    @Test
    public void FailTestGetProfile() throws Exception{

        Map<String, String> result = new HashMap<String, String>();
        result.put("username", username);
        result.put("profileImageUrl", profileImageUrl);

        when(membershipClient.findProfile(anyLong()))
                .thenReturn(result);

        mockMvc.perform(get("/profile/1"))
                .andDo(print())
                .andExpectAll(status().isOk(),
                        jsonPath("$.username").value(username),
                        jsonPath("$.profileImageUrl").value(profileImageUrl));
    }
}
