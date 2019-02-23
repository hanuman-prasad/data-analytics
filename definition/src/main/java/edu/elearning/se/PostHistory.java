package edu.elearning.se;

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


}
