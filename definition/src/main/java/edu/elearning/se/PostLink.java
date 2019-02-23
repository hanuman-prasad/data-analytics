package edu.elearning.se;

import com.google.common.base.MoreObjects;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Builder
@Getter
@EqualsAndHashCode (onlyExplicitlyIncluded = true)
public class PostLink implements AsteriModel {

    private String creationDate;

    @NonNull
    @EqualsAndHashCode.Include
    private String id;
    private String linkTypeId;
    private String postId;
    private String relatedPostId;

    public String getCreationDate() {
        return creationDate;
    }

    public String getId() {
        return id;
    }

    public String getLinkTypeId() {
        return linkTypeId;
    }

    public String getPostId() {
        return postId;
    }

    public String getRelatedPostId() {
        return relatedPostId;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("creationDate", creationDate)
                .add("id", id)
                .add("linkTypeId", linkTypeId)
                .add("postId", postId)
                .add("relatedPostId", relatedPostId)
                .toString();
    }
}
