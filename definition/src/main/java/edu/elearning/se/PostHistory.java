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
public class PostHistory implements AsteriModel {

    private LocalDateTime creationDate;
    private PostHistoryTypeId postHistoryTypeId;

    private String comment;

    @NonNull
    @EqualsAndHashCode.Include
    private String id;

    @NonNull
    @EqualsAndHashCode.Include
    private String postId;
    private String revisionGuId;
    private String text;
    private String userId;


    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public PostHistoryTypeId getPostHistoryTypeId() {
        return postHistoryTypeId;
    }

    public String getComment() {
        return comment;
    }

    public String getId() {
        return id;
    }

    public String getPostId() {
        return postId;
    }

    public String getRevisionGuId() {
        return revisionGuId;
    }

    public String getText() {
        return text;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("creationDate", creationDate)
                .add("postHistoryTypeId", postHistoryTypeId)
                .add("comment", comment)
                .add("id", id)
                .add("postId", postId)
                .add("revisionGuId", revisionGuId)
                .add("text", text)
                .add("userId", userId)
                .toString();
    }
}
