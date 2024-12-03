package board_project3_backend.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Category {
    private int id;
    private String categoryName;
}
