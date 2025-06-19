package portfolio.mq.featureflag.adapter.out.persistnece;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import portfolio.mq.featureflag.domain.model.FeatureFlag;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static portfolio.mq.fixtures.Fixtures.aDefaultFlag;
import static portfolio.mq.fixtures.Fixtures.aFlag;
import static portfolio.mq.featureflag.domain.model.FlagType.BOOLEAN;

class InMemoryFeatureFlagRepositoryTest {
    private InMemoryFeatureFlagRepository repository;

    @BeforeEach
    void setUp() {
        repository = new InMemoryFeatureFlagRepository();
    }

    @Test
    void testSaveAndFindByFlagKey() {
        String flagKey = "test-key";
        String flagName = "test-flag";
        FeatureFlag flag = aFlag(
            flagKey,
            flagName,
            "",
            BOOLEAN,
            "test-user",
            null
        );

        repository.save(flag);

        FeatureFlag retrievedFlag = repository.findByFlagKey(flagKey);

        assertNotNull(retrievedFlag);
        assertEquals(flagKey, retrievedFlag.getFlagKey());
        assertEquals(flagName, retrievedFlag.getName());
        assertEquals(BOOLEAN, retrievedFlag.getType());
    }

    @Test
    void testFindAll() {
        FeatureFlag flag1 = aDefaultFlag();
        FeatureFlag flag2 = aDefaultFlag();

        repository.save(flag1);
        repository.save(flag2);

        List<FeatureFlag> flags = repository.findAll();

        assertEquals(2, flags.size());
        assertTrue(flags.contains(flag1));
        assertTrue(flags.contains(flag2));
    }

    @Test
    void testDeleteByFlagKey() {
        String flagKey = "test-key";
        String flagName = "test-flag";
        FeatureFlag flag = aFlag(
            flagKey,
            flagName,
            "",
            BOOLEAN,
            "test-user",
            null
        );

        repository.save(flag);
        assertNotNull(repository.findByFlagKey(flagKey));

        repository.deleteByFlagKey(flagKey);
        assertNull(repository.findByFlagKey(flagKey));
    }
}