package com.example.spring.actuator.gettingstarted.endpoints;

import com.example.spring.actuator.gettingstarted.utils.TestUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EndpointsIT
{
    // ============================== [Fields] ==============================

    // -------------------- [Private Fields] --------------------

    @Autowired
    private MockMvc mvc;

    @Autowired
    private TestUtils testUtils;

    // ============================== [Unit Tests] ==============================

    // -------------------- [Test Helper Classes] --------------------

    // -------------------- [Test Helper Methods] --------------------

    // -------------------- [Test Initialization] --------------------

    // -------------------- [Tests] --------------------

    @Test
    void actuator() throws Exception
    {
        String resultStr = mvc.perform(get("/actuator")).andDo(print()).andExpect(status().isOk()).andReturn().getResponse()
                              .getContentAsString();

        testUtils.dumpPretty(resultStr);
    }

    @Test
    void info() throws Exception
    {
        String resultStr = mvc.perform(get("/actuator/info")).andDo(print()).andExpect(status().isOk()).andReturn().getResponse()
                              .getContentAsString();

        testUtils.dumpPretty(resultStr);
    }

    @Test
    void metrics() throws Exception
    {
        String resultStr = mvc.perform(get("/actuator/metrics")).andDo(print()).andExpect(status().isOk()).andReturn().getResponse()
                              .getContentAsString();

        testUtils.dumpPretty(resultStr);
    }

    @Test
    void metrics_httpServerRequests() throws Exception
    {
        // At least one request has to be performed before the metrics are available.
        mvc.perform(get("/hello")).andDo(print());

        String resultStr = mvc.perform(get("/actuator/metrics/http.server.requests")).andDo(print()).andExpect(status().isOk()).andReturn().getResponse()
                              .getContentAsString();

        testUtils.dumpPretty(resultStr);
    }

    @Test
    void metrics_httpServerRequests_hello() throws Exception
    {
        // At least one request has to be performed before the metrics are available.
        mvc.perform(get("/hello")).andDo(print());
        mvc.perform(get("/hello")).andDo(print());
        mvc.perform(get("/hello")).andDo(print());

        String resultStr = mvc.perform(get("/actuator/metrics/http.server.requests?tag=uri:/hello")).andDo(print()).andExpect(status().isOk()).andReturn().getResponse()
                              .getContentAsString();

        testUtils.dumpPretty(resultStr);
    }

    @Test
    void httptrace() throws Exception
    {
        mvc.perform(get("/hello")).andDo(print());

        String resultStr = mvc.perform(get("/actuator/httptrace")).andDo(print()).andExpect(status().isOk()).andReturn().getResponse()
                              .getContentAsString();

        testUtils.dumpPretty(resultStr);
    }
}
