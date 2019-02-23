package edu.elearning.se;

import com.google.common.base.MoreObjects;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDateTime;

@Builder
@Getter
@EqualsAndHashCode (onlyExplicitlyIncluded = true)
public class Badge implements AsteriModel {

    private BadgeClass badgeClass;
    private LocalDateTime date;

    @NonNull
    @EqualsAndHashCode.Include
    private String id;
    private String name;
    private String tagBased;
    private String userId;

    public BadgeClass getBadgeClass() {
        return badgeClass;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTagBased() {
        return tagBased;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("badgeClass", badgeClass)
                .add("date", date)
                .add("id", id)
                .add("name", name)
                .add("tagBased", tagBased)
                .add("userId", userId)
                .toString();
    }
}
