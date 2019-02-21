package edu.elearning.se;


import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDateTime;

@Builder
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Post {

    @NonNull
    @EqualsAndHashCode.Include
    private String id;
    private String postTypeId;
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

}
