package edu.elearning.se;

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
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", displayName='" + displayName + '\'' +
                ", location='" + location + '\'' +
                ", accountId='" + accountId + '\'' +
                ", upVotes='" + upVotes + '\'' +
                ", downVotes='" + downVotes + '\'' +
                ", reputation='" + reputation + '\'' +
                ", views='" + views + '\'' +
                ", websiteUrl='" + websiteUrl + '\'' +
                ", aboutMe='" + aboutMe + '\'' +
                ", profileImageUrl='" + profileImageUrl + '\'' +
                ", creationDate=" + creationDate +
                ", lastAccessDate=" + lastAccessDate +
                ", userWebsite=" + userWebsite +
                '}';
    }
}
