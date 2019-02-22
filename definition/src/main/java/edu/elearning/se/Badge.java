package edu.elearning.se;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDateTime;

@Builder
@Getter
@EqualsAndHashCode (onlyExplicitlyIncluded = true)
public class Badge {

    private String BadgeClass;
    private LocalDateTime Date;

    @NonNull
    @EqualsAndHashCode.Include
    private String id;
    private String name;
    private String tagBased;
    private String userId;
}
