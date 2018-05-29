package com.chain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by lian.ran on 2018/4/2.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ButterflyWebApplication.class)
public class MovModeTest {

    // 注入一个web应用环境(容器)
    @Autowired
    private WebApplicationContext webApplicationContext;

    // MVC环境对象
    private MockMvc mockMvc;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testCreateAccount(){
        String content = "{\"name\":\"lemon\",\"money\":\"123456\"}";
        try {
            mockMvc.perform(MockMvcRequestBuilders.post("/account/create")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .content(content))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void create3() throws Exception {
        Date date = new Date(LocalDateTime.now().plusYears(1).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        String content = "{\"username\":null,\"password\":null,\"birthday\":" + date.getTime() + "}";
        mockMvc.perform(MockMvcRequestBuilders.post("/user3")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(3));
    }

    @Test
    public void create4() throws Exception {
        Date date = new Date(LocalDateTime.now().plusYears(1).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        String content = "{\"username\":null,\"password\":null,\"birthday\":" + date.getTime() + ",\"idCard\":\"12345678\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/user4")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(4));
    }
}
