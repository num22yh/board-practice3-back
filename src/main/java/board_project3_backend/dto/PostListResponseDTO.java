package board_project3_backend.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter @Setter
@Builder
public class PostListResponseDTO {
    private long id;
    private String author;
    private String title;
    private String content;
    private int viewCount;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String categoryName;
    private boolean hasAttachments;
}
