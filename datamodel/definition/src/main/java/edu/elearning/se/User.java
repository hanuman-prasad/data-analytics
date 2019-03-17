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
public class User implements AsteriModel {


    @NonNull
    @EqualsAndHashCode.Include
    private String id;
    @NonNull
    @EqualsAndHashCode.Include
    private String displayName;
    private String location;

    private String accountId;
    private String upVotes;
    private String downVotes;
    private String reputation;
    private String views;
    private String websiteUrl;
    private String aboutMe;
    private String profileImageUrl;

    @NonNull
    private LocalDateTime creationDate;

    @NonNull
    private LocalDateTime lastAccessDate;

    @NonNull
    private UserWebsite userWebsite;

    @Override
    public String getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getLocation() {
        return location;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getUpVotes() {
        return upVotes;
    }

    public String getDownVotes() {
        return downVotes;
    }

    public String getReputation() {
        return reputation;
    }

    public String getViews() {
        return views;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public LocalDateTime getLastAccessDate() {
        return lastAccessDate;
    }

    public UserWebsite getUserWebsite() {
        return userWebsite;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("displayName", displayName)
                .add("location", location)
                .add("accountId", accountId)
                .add("upVotes", upVotes)
                .add("downVotes", downVotes)
                .add("reputation", reputation)
                .add("views", views)
                .add("websiteUrl", websiteUrl)
                .add("aboutMe", aboutMe)
                .add("profileImageUrl", profileImageUrl)
                .add("creationDate", creationDate)
                .add("lastAccessDate", lastAccessDate)
                .add("userWebsite", userWebsite)
                .toString();
    }
}
