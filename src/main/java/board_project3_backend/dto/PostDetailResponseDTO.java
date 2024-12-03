package board_project3_backend.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter @Setter
@Builder
public class PostDetailResponseDTO {
    private Long id;
    private String title;
    private String content;
    private String author;
    private String categoryName;
    private int viewCount;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private List<AttachmentDTO> attachments;
}
