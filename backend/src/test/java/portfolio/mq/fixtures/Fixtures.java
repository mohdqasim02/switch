package portfolio.mq.fixtures;

import portfolio.mq.model.FeatureFlag;
import portfolio.mq.model.FlagType;

import java.time.LocalDateTime;

public class Fixtures {
    private static int id = 1;

    public static FeatureFlag aFlag(
        String key,
        String name,
        String description,
        FlagType type,
        String createdBy,
        LocalDateTime createdAt
    ) {
        return new FeatureFlag(key, name, description, type, createdBy, createdAt);
    }

    public static FeatureFlag aDefaultFlag() {
        return aFlag(
            "defaultFlag" + id++,
            "Default Flag",
            "This is a default flag",
            FlagType.BOOLEAN,
            "defaultUser",
            null
        );
    }
}