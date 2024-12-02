package board_project3_backend.service;

import board_project3_backend.entity.Post;
import board_project3_backend.repository.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostMapper postMapper;

    public List<Post> getPostList(){
        return postMapper.getPostList();
    }
}
