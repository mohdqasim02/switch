package portfolio.mq.featureflag.application.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import portfolio.mq.featureflag.application.dto.FeatureFlagDto;
import portfolio.mq.featureflag.domain.model.FeatureFlag;
import portfolio.mq.featureflag.domain.repository.FeatureFlagRepository;

import java.util.List;

@Service
public class FeatureFlagService {

    private final FeatureFlagRepository flagRepository;

    public FeatureFlagService(FeatureFlagRepository flagRepository) {
        this.flagRepository = flagRepository;
    }

    public List<FeatureFlagDto> getAllFlags() {
        return flagRepository.findAll().stream()
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

        FeatureFlag saved = flagRepository.save(flag);
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

    @Transactional
    public FeatureFlagDto updateFlagStatus(String key) {
        FeatureFlag flag = flagRepository.findByFlagKey(key);

        flag.toggleIsEnabled();

        return mapToDTO(flagRepository.save(flag));
    }
}

