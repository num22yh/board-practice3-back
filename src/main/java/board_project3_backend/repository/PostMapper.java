package board_project3_backend.repository;

import board_project3_backend.entity.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostMapper {

    List<Post> getPostList();

    void insertPost(Post post);

    Post getPostById(long id);
}
