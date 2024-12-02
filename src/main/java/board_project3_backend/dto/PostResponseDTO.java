package board_project3_backend.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class PostResponseDTO {
    private int id;
    private String author;
    private String title;
    private String content;
    private int viewCount;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String categoryName;
    private boolean hasAttachments;
}
