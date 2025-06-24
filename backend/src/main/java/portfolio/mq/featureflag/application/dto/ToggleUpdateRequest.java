package portfolio.mq.featureflag.application.dto;

public record ToggleUpdateRequest(Boolean isEnabled, String comment) {}