package com.example.spring.actuator.gettingstarted.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestUtils
{
    // ============================== [Fields] ==============================

    // -------------------- [Private Fields] --------------------

    @Autowired
    private ObjectMapper objectMapper;

    // ============================== [Spring Beans] ==============================

    // -------------------- [Public Spring Beans] --------------------

    // ============================== [Getter/Setter] ==============================

    // -------------------- [Private Getter/Setter] --------------------

    // -------------------- [Public Getter/Setter] --------------------

    // ============================== [Methods] ==============================

    // -------------------- [Private Methods] --------------------

    // -------------------- [Public Methods] --------------------

    public void dumpPretty(String jsonStr) throws JsonProcessingException
    {
        Object jsonObj = this.objectMapper.readValue(jsonStr, Object.class);
        String jsonPrettyStr = this.objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObj);

        System.out.println(jsonPrettyStr);
    }
}
