package portfolio.mq.repository;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;
import portfolio.mq.model.FeatureFlag;

import java.util.List;

@Repository
@ConditionalOnProperty(name = "env", havingValue = "env", matchIfMissing = true)
public class PostgresFeatureFlagRepository implements FeatureFlagRepository {
    private final FeatureFlagJpaRepository jpaRepository;

    public PostgresFeatureFlagRepository(FeatureFlagJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public FeatureFlag save(FeatureFlag featureFlag) {
        return jpaRepository.save(featureFlag);
    }

    @Override
    public FeatureFlag findByFlagKey(String key) {
        return jpaRepository.findByFlagKey(key);
    }

    @Override
    public List<FeatureFlag> findAll() {
        return jpaRepository.findAll();
    }

    @Override
    public void deleteByFlagKey(String key) {
        jpaRepository.deleteByFlagKey(key);
    }
}
