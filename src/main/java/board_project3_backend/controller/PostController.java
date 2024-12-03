package board_project3_backend.controller;

import board_project3_backend.dto.*;
import board_project3_backend.entity.Post;
import board_project3_backend.repository.AttachmentMapper;
import board_project3_backend.service.AttachmentService;
import board_project3_backend.service.CategoryService;
import board_project3_backend.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final CategoryService categoryService;
    private final AttachmentService attachmentService;
    private final AttachmentMapper attachmentMapper;

    @GetMapping
    public List<PostListResponseDTO> getPostList() {
        return postService.getPostList().stream()
                .map(post -> PostListResponseDTO.builder()
                        .id(post.getId())
                        .author(post.getAuthor())
                        .title(post.getTitle())
                        .content(post.getContent())
                        .viewCount(post.getViewCount())
                        .createdAt(post.getCreatedAt())
                        .updatedAt(post.getUpdatedAt())
                        .categoryName(post.getCategoryName())
                        .hasAttachments(post.isHasAttachments())
                        .build()
                )
                .collect(Collectors.toList());
    }

    @GetMapping("/create")
    public List<CategoryListResponseDTO> getCategories() {
        return categoryService.getCategoryList().stream()
                .map(category -> CategoryListResponseDTO.builder()
                        .id(category.getId())
                        .categoryName(category.getCategoryName())
                        .build())
                .collect(Collectors.toList());
    }


    @PostMapping("/create")
    public ResponseEntity<?> createPost(@ModelAttribute PostCreateRequestDTO postCreateRequestDTO) {
        try {
            Post post = Post.builder()
                    .title(postCreateRequestDTO.getTitle())
                    .content(postCreateRequestDTO.getContent())
                    .author(postCreateRequestDTO.getAuthor())
                    .password(postCreateRequestDTO.getPassword())
                    .categoryId(postCreateRequestDTO.getCategoryId())
                    .build();

            postService.createPost(post, postCreateRequestDTO.getAttachments());
            return ResponseEntity.ok("게시글이 성공적으로 생성되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("게시글 생성 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public PostDetailResponseDTO getPostDetail(@PathVariable Long id) {

        Post post = postService.getPostById(id);

        List<AttachmentDTO> attachments = attachmentMapper.getAttachmentsByPostId(post.getId())
                .stream()
                .map(attachment -> AttachmentDTO.builder()
                        .originalName(attachment.getOriginalName())
                        .storedName(attachment.getStoredName())
                        .url("/api/posts/attachments/" + attachment.getStoredName())
                        .build())
                .collect(Collectors.toList());

        return PostDetailResponseDTO.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .author(post.getAuthor())
                .categoryName(post.getCategoryName())
                .viewCount(post.getViewCount())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .attachments(attachments)
                .build();
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> downloadAttachment(@PathVariable String fileName) {
        try {
            return attachmentService.downloadAttachment(fileName);
        } catch (Exception e) {
            throw new RuntimeException("파일 다운로드 중 오류가 발생했습니다.", e);
        }
    }


}
