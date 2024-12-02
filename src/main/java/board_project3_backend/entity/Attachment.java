package board_project3_backend.entity;

import lombok.Data;

@Data
public class Attachment {
    private int id;
    private int postId;
    private String originalName;
    private String storedName;
    private String logicalPath;
    private String physicalPath;
    private long size;
}
