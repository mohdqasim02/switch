package portfolio.mq.repository;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import portfolio.mq.model.FeatureFlag;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ConditionalOnProperty(name = "env", havingValue = "local")
public class InMemoryFeatureFlagRepository implements FeatureFlagRepository {
    private final Map<String, FeatureFlag> flagStore = new ConcurrentHashMap<>();

    @Override
    public FeatureFlag save(FeatureFlag featureFlag) {
        flagStore.put(featureFlag.getFlagKey(), featureFlag);
        return featureFlag;
    }

    @Override
    public FeatureFlag findByFlagKey(String key) {
        return flagStore.get(key);
    }

    @Override
    public List<FeatureFlag> findAll() {
        return new ArrayList<>(flagStore.values());
    }

    @Override
    public void deleteByFlagKey(String key) {
        flagStore.remove(key);
    }
}
