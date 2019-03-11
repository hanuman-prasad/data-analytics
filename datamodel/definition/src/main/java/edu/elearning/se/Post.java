package edu.elearning.se;


import com.google.common.base.MoreObjects;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDateTime;

@Builder
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Post implements AsteriModel {

    @NonNull
    @EqualsAndHashCode.Include
    private String id;
    private String acceptedAnswerId;
    private String score;
    private String viewCount;
    private String body;
    private String ownerUserId;
    private String lastEditorUserId;
    private String title;
    private String tags;
    private String answerCount;
    private String commentCount;
    private String favoriteCount;

    private LocalDateTime creationDate;
    private LocalDateTime lastEditDate;
    private LocalDateTime lastActivityDate;

    @NonNull
    private UserWebsite userWebsite;

    private PostType postType;

    public String getId() {
        return id;
    }

    public String getAcceptedAnswerId() {
        return acceptedAnswerId;
    }

    public String getScore() {
        return score;
    }

    public String getViewCount() {
        return viewCount;
    }

    public String getBody() {
        return body;
    }

    public String getOwnerUserId() {
        return ownerUserId;
    }

    public String getLastEditorUserId() {
        return lastEditorUserId;
    }

    public String getTitle() {
        return title;
    }

    public String getTags() {
        return tags;
    }

    public String getAnswerCount() {
        return answerCount;
    }

    public String getCommentCount() {
        return commentCount;
    }

    public String getFavoriteCount() {
        return favoriteCount;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public LocalDateTime getLastEditDate() {
        return lastEditDate;
    }

    public LocalDateTime getLastActivityDate() {
        return lastActivityDate;
    }

    public PostType getPostType() {
        return postType;
    }

    public UserWebsite getUserWebsite() {
        return userWebsite;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("acceptedAnswerId", acceptedAnswerId)
                .add("score", score)
                .add("viewCount", viewCount)
                .add("body", body)
                .add("ownerUserId", ownerUserId)
                .add("lastEditorUserId", lastEditorUserId)
                .add("title", title)
                .add("tags", tags)
                .add("answerCount", answerCount)
                .add("commentCount", commentCount)
                .add("favoriteCount", favoriteCount)
                .add("creationDate", creationDate)
                .add("lastEditDate", lastEditDate)
                .add("lastActivityDate", lastActivityDate)
                .add("userWebsite", userWebsite)
                .add("postType", postType)
                .toString();
    }
}
