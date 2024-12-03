package board_project3_backend.service;

import board_project3_backend.entity.Attachment;
import board_project3_backend.entity.Post;
import board_project3_backend.repository.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostMapper postMapper;
    private final AttachmentService attachmentService;

    public List<Post> getPostList(){
        return postMapper.getPostList();
    }

    @Transactional
    public void createPost( Post post , List<MultipartFile> files){

        postMapper.insertPost(post);

        if (files != null && !files.isEmpty()) {
            attachmentService.saveAttachments(files, post.getId());
        }

    }

    public Post getPostById(Long id) {
        Post post = postMapper.getPostById(id);
        if (post == null) {
            throw new RuntimeException("게시글을 찾을 수 없습니다. ID: " + id);
        }
        return post;
    }

}
