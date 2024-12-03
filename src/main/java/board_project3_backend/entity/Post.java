package board_project3_backend.entity;


import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;


@Data
@Builder
public class Post {
    private long id;
    private String author;
    private String password;
    private String title;
    private String content;
    private int viewCount = 0;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private int categoryId;
    private String categoryName;
    private boolean hasAttachments;
}
