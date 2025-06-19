package portfolio.mq.featureflag.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "feature_flag")
public class FeatureFlag {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "flay_key", unique = true, nullable = false)
    private String flagKey;

    @Column(name = "created_by", nullable = false)
    private String createdBy;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FlagType type;

    private String name;
    private String description;
    private Boolean isEnabled;

    @PrePersist
    public void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
        this.updatedAt = LocalDateTime.now();
    }

    @PostUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public FeatureFlag(
        String flagKey,
        String name,
        String description,
        FlagType type,
        String createdBy,
        LocalDateTime createdAt
    ) {
        this.flagKey = flagKey;
        this.name = name;
        this.description = description;
        this.type = type;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.isEnabled = false;
    }
}
