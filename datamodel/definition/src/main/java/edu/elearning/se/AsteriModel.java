package edu.elearning.se;

import java.io.Serializable;
import java.util.UUID;

public interface AsteriModel extends Serializable {
    String getId();
    UserWebsite getUserWebsite();
    UUID getObjectId();

    // TODO need to fix this
    void setObjectId(UUID objectId);
}
