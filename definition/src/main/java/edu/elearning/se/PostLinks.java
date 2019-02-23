package edu.elearning.se;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Builder
@Getter
@EqualsAndHashCode (onlyExplicitlyIncluded = true)
public class PostLinks implements AsteriModel {

    private String creationDate;

    @NonNull
    @EqualsAndHashCode.Include
    private String id;
    private String linkTypeId;
    private String postId;
    private String relatedPostId;
}
