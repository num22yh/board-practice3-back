package board_project3_backend.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter @Setter
@Builder
public class PostCreateRequestDTO {

    private String title;
    private String content;
    private String author;
    private String password;
    private int categoryId;
    private List<MultipartFile> attachments ;

}
