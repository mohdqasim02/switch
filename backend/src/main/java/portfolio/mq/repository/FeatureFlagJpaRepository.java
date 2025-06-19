package portfolio.mq.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import portfolio.mq.model.FeatureFlag;

import java.util.UUID;

public interface FeatureFlagJpaRepository extends JpaRepository<FeatureFlag, UUID> {
    FeatureFlag findByFlagKey(String key);
    void deleteByFlagKey(String key);
}
