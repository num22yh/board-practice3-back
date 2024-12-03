package board_project3_backend.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Attachment {
    private long id;
    private long postId;
    private String originalName;
    private String storedName;
    private String logicalPath;
    private String physicalPath;
    private long size;
}
