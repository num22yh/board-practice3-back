package board_project3_backend.entity;


import lombok.Data;

import java.sql.Timestamp;
import java.util.List;


@Data
public class Post {
    private int id;
    private String author;
    private String password;
    private String title;
    private String content;
    private int view_count;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    private String categoryName;
    private boolean hasAttachments;
}
