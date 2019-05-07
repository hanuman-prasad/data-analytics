package edu.elearning.se;

import com.google.common.base.MoreObjects;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Builder
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Tag extends VersionedEntity {

    private String count;
    private String excerptPostId;
    @NonNull
    @EqualsAndHashCode.Include
    private String id;
    private String tagName;
    private String wikiPostId;
    @NonNull
    private UserWebsite userWebsite;

    public String getCount() {
        return count;
    }

    public String getExcerptPostId() {
        return excerptPostId;
    }

    public String getId() {
        return id;
    }

    public String getTagName() {
        return tagName;
    }

    public String getWikiPostId() {
        return wikiPostId;
    }

    public UserWebsite getUserWebsite() {
        return userWebsite;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("count", count)
                .add("excerptPostId", excerptPostId)
                .add("id", id)
                .add("tagName", tagName)
                .add("wikiPostId", wikiPostId)
                .add("userWebsite", userWebsite)
                .toString();
    }
}
