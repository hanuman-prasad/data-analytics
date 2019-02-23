package edu.elearning.se;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDateTime;

@Builder
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Comment implements AsteriModel {

    @NonNull
    @EqualsAndHashCode.Include
    private String id;
    private String postId;
    private String score;
    private String text;
    private LocalDateTime creationDate;
    private String userId;

    public String getId() {
        return id;
    }

    public String getPostId() {
        return postId;
    }

    public String getScore() {
        return score;
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id='" + id + '\'' +
                ", postId='" + postId + '\'' +
                ", score='" + score + '\'' +
                ", text='" + text + '\'' +
                ", creationDate=" + creationDate +
                ", userId='" + userId + '\'' +
                '}';
    }
}
