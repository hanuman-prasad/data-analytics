package edu.elearning.se;

import com.google.common.base.MoreObjects;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Builder
@Getter
@EqualsAndHashCode (onlyExplicitlyIncluded = true)
public class Vote implements AsteriModel {

    @NonNull
    @EqualsAndHashCode.Include
    private String id;
    private String postId;
    private String voteTypeId;
    private String creationDate;

    public String getId() {
        return id;
    }

    public String getPostId() {
        return postId;
    }

    public String getVoteTypeId() {
        return voteTypeId;
    }

    public String getCreationDate() {
        return creationDate;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("postId", postId)
                .add("voteTypeId", voteTypeId)
                .add("creationDate", creationDate)
                .toString();
    }
}
