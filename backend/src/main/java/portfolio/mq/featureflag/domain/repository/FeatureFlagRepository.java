package portfolio.mq.featureflag.domain.repository;

import portfolio.mq.featureflag.domain.model.FeatureFlag;

import java.util.List;

public interface FeatureFlagRepository {
    FeatureFlag save(FeatureFlag featureFlag);
    FeatureFlag findByFlagKey(String key);
    List<FeatureFlag> findAll();
    void deleteByFlagKey(String key);
}

