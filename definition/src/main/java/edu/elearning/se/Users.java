package edu.elearning.se;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import java.time.LocalDateTime;

@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Users {



    @NonNull
    @EqualsAndHashCode.Include
    private String id;
    @NonNull
    @EqualsAndHashCode.Include
    private String displayName;
    private String location;
    @NonNull
    @EqualsAndHashCode.Include
    private String accountId;
    private String upVotes;
    private String downVotes;
    private String reputation;
    private String views;
    private String WebsiteUrl;
    private String aboutMe;
    private String profileImageUrl;

    @NonNull
    private LocalDateTime creationDate;

    @NonNull
    private LocalDateTime LastAccessDate;

    private UserWebsite userWebsite;
}
