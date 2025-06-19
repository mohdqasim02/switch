package portfolio.mq.featureflag.application.service;

import org.springframework.stereotype.Service;
import portfolio.mq.featureflag.application.dto.FeatureFlagDto;
import portfolio.mq.featureflag.domain.model.FeatureFlag;
import portfolio.mq.featureflag.domain.repository.FeatureFlagRepository;

import java.util.List;

@Service
public class FeatureFlagService {

    private final FeatureFlagRepository repository;

    public FeatureFlagService(FeatureFlagRepository repository) {
        this.repository = repository;
    }

    public List<FeatureFlagDto> getAllFlags() {
        return repository.findAll().stream()
            .map(this::mapToDTO)
            .toList();
    }

    public FeatureFlagDto createFlag(FeatureFlagDto dto) {
        FeatureFlag flag = new FeatureFlag(
            dto.flagKey(),
            dto.name(),
            dto.description(),
            dto.type(),
            dto.createdBy(),
            dto.createdAt()
        );

        FeatureFlag saved = repository.save(flag);
        return mapToDTO(saved);
    }

    public FeatureFlagDto mapToDTO(FeatureFlag flag) {
        return new FeatureFlagDto(
            flag.getFlagKey(),
            flag.getName(),
            flag.getDescription(),
            flag.getType(),
            flag.getIsEnabled(),
            flag.getCreatedBy(),
            flag.getCreatedAt()
        );
    }
}

