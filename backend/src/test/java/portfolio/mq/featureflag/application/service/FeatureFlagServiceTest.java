package portfolio.mq.featureflag.application.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import portfolio.mq.featureflag.application.dto.FeatureFlagDto;
import portfolio.mq.featureflag.domain.model.FeatureFlag;
import portfolio.mq.featureflag.domain.model.FlagType;
import portfolio.mq.featureflag.domain.repository.FeatureFlagRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static portfolio.mq.fixtures.Fixtures.aFlag;

@ExtendWith(MockitoExtension.class)
class FeatureFlagServiceTest {
    @InjectMocks
    private FeatureFlagService featureFlagService;

    @Mock
    private FeatureFlagRepository repository;


    @Test
    void getAllFlags() {
        FeatureFlag flag = aFlag(
            "key1",
            "name1",
            "desc1",
            FlagType.BOOLEAN,
            "user1",
            null
        );

        Mockito.when(repository.findAll()).thenReturn(List.of(flag));

        List<FeatureFlagDto> result = featureFlagService.getAllFlags();

        assertEquals(1, result.size());
        assertEquals("key1", result.get(0).flagKey());
        assertEquals("name1", result.get(0).name());
    }

    @Test
    void createFlag() {
        FeatureFlagDto dto = new FeatureFlagDto(
            "key1",
            "name1",
            "desc1",
            FlagType.BOOLEAN,
            true,
            "user1",
            null
        );
        FeatureFlag flag = aFlag(
            "key1",
            "name1",
            "desc1",
            FlagType.BOOLEAN,
            "user1",
            null
        );

        Mockito.when(repository.save(Mockito.any(FeatureFlag.class))).thenReturn(flag);

        FeatureFlagDto result = featureFlagService.createFlag(dto);

        assertEquals("key1", result.flagKey());
        assertEquals("name1", result.name());
        assertEquals("desc1", result.description());
    }
}