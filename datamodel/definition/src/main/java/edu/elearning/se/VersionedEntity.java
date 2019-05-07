package edu.elearning.se;

import com.google.common.base.MoreObjects;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;

@Builder
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class VersionedEntity implements AsteriModel {

    @NonNull
    @EqualsAndHashCode.Include
    private UUID objectId;

    public UUID getObjectId() {
        return objectId;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("objectId", objectId)
                .toString();
    }
}
