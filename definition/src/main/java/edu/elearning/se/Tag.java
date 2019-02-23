package edu.elearning.se;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Builder
@Getter
@EqualsAndHashCode (onlyExplicitlyIncluded = true)
public class Tag implements AsteriModel {

    private String count;
    private String excerptPostId;
    @NonNull
    @EqualsAndHashCode.Include
    private String id;
    private String tagName;
    private String wikiPostId;
}
