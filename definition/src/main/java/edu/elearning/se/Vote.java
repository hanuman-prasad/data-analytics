package edu.elearning.se;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Builder
@Getter
@EqualsAndHashCode (onlyExplicitlyIncluded = true)
public class Vote {

    @NonNull
    @EqualsAndHashCode.Include
    private String id;
    private String postId;
    private String voteTypeId;
    private String creationDate;
}
