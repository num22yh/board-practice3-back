package board_project3_backend.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AttachmentDTO {
    private String originalName;
    private String storedName;
    private String url;
}
