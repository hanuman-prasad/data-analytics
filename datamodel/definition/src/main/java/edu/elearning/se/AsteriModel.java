package edu.elearning.se;

import java.io.Serializable;
import java.util.UUID;

public interface AsteriModel extends Serializable {
    String getId();
    UserWebsite getUserWebsite();
    UUID getObjectId();
}
