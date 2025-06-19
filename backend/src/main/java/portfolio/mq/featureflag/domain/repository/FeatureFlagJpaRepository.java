package portfolio.mq.featureflag.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import portfolio.mq.featureflag.domain.model.FeatureFlag;

import java.util.UUID;

public interface FeatureFlagJpaRepository extends JpaRepository<FeatureFlag, UUID> {
    FeatureFlag findByFlagKey(String key);
    void deleteByFlagKey(String key);
}
