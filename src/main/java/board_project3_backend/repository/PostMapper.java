package board_project3_backend.repository;

import board_project3_backend.entity.Post;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {

    List<Post> getPostList();
}
