package board_project3_backend.controller;

import board_project3_backend.dto.PostResponseDTO;
import board_project3_backend.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public List<PostResponseDTO> getPostList() {
        return postService.getPostList().stream()
                .map(post -> {
                    PostResponseDTO dto = new PostResponseDTO();
                    dto.setId(post.getId());
                    dto.setAuthor(post.getAuthor());
                    dto.setTitle(post.getTitle());
                    dto.setContent(post.getContent());
                    dto.setViewCount(post.getView_count());
                    dto.setCreatedAt(post.getCreatedAt());
                    dto.setUpdatedAt(post.getUpdatedAt());
                    dto.setCategoryName(post.getCategoryName());
                    dto.setHasAttachments(post.isHasAttachments());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
