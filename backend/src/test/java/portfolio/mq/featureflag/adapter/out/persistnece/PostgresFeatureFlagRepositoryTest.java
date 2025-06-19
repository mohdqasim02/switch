package portfolio.mq.featureflag.adapter.out.persistnece;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import portfolio.mq.featureflag.domain.model.FeatureFlag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static portfolio.mq.fixtures.Fixtures.aDefaultFlag;

@DataJpaTest
@Import(PostgresFeatureFlagRepository.class)
class PostgresFeatureFlagRepositoryTest {
    @Autowired
    private PostgresFeatureFlagRepository flagRepository;

    @Test
    void testFindByFlagKey() {
        FeatureFlag featureFlag = aDefaultFlag();

        flagRepository.save(featureFlag);

        FeatureFlag foundFeatureFlag = flagRepository.findByFlagKey(featureFlag.getFlagKey());

        assertEquals(featureFlag.getFlagKey(), foundFeatureFlag.getFlagKey());
    }
}