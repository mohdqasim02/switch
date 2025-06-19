package portfolio.mq.featureflag.application.dto;

import portfolio.mq.featureflag.domain.model.FlagType;
import java.time.LocalDateTime;

public record FeatureFlagDto(
    String flagKey,
    String name,
    String description,
    FlagType type,
    Boolean isEnabled,
    String createdBy,
    LocalDateTime createdAt
) {}
