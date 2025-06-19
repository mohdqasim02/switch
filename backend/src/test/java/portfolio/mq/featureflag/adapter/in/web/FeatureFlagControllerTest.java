package portfolio.mq.featureflag.adapter.in.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import portfolio.mq.featureflag.application.dto.FeatureFlagDto;
import portfolio.mq.featureflag.application.service.FeatureFlagService;
import portfolio.mq.featureflag.domain.model.FlagType;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@WebMvcTest(FeatureFlagController.class)
class FeatureFlagControllerTest {
    @MockBean
    private FeatureFlagService service;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnAllFeatureFlags() throws Exception {
        List<FeatureFlagDto> flags = List.of(
            new FeatureFlagDto("key1", "Flag 1", "Description 1", FlagType.BOOLEAN, true, "user1", LocalDateTime.now()),
            new FeatureFlagDto("key2", "Flag 2", "Description 2", FlagType.BOOLEAN, false, "user2", LocalDateTime.now())
        );

        when(service.getAllFlags()).thenReturn(flags);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/toggles")
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(flags.size()))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].flagKey").value("key1"))
            .andExpect(MockMvcResultMatchers.jsonPath("$[1].flagKey").value("key2"));
    }

    @Test
    void shouldCreateFeatureFlag() throws Exception {
        FeatureFlagDto dto = new FeatureFlagDto("key3", "Flag 3", "Description 3", FlagType.BOOLEAN, true, "user3", LocalDateTime.now());

        when(service.createFlag(any(FeatureFlagDto.class))).thenReturn(dto);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/toggles/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andExpect(MockMvcResultMatchers.jsonPath("$.flagKey").value("key3"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Flag 3"));
    }
}