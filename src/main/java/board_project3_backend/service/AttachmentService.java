package board_project3_backend.service;

import board_project3_backend.entity.Attachment;
import board_project3_backend.repository.AttachmentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AttachmentService {

    private final AttachmentMapper attachmentMapper;
    private final String uploadDir = "C:/Users/num22/Desktop/board-practice3/board-practice3-back/uploads";

    public AttachmentService(AttachmentMapper attachmentMapper){
        this.attachmentMapper = attachmentMapper;

        Path uploadPath = Paths.get(uploadDir);
        if(!Files.exists(uploadPath)){
            try{
                Files.createDirectories(uploadPath);
            }catch(IOException e){
                throw new RuntimeException("파일 저장 디렉터리를 생성할 수 없습니다.", e);
            }
        }
    }


    public void saveAttachments(List<MultipartFile> files, long postId){

        if (files == null || files.isEmpty()) return;

        List<Attachment> attachments = new ArrayList<>();
        for(MultipartFile file : files){
            try{
                String storedFilename = saveFile(file);

                // 첨부파일 엔터티 생성
                Attachment attachment = Attachment.builder()
                        .postId(postId)
                        .originalName(file.getOriginalFilename())
                        .storedName(storedFilename)
                        .logicalPath("/uploads/")
                        .physicalPath(uploadDir + "/" + storedFilename)
                        .size(file.getSize())
                        .build();

                attachments.add(attachment);
            }catch (IOException e) {
                throw new RuntimeException("파일 저장 중 오류가 발생했습니다: " + file.getOriginalFilename(), e);
            }
        }

        attachmentMapper.insertAttachments(attachments);
    }

    public ResponseEntity<Resource> downloadAttachment(String fileName) {
        try {

            Path filePath = Paths.get("uploads").resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (!resource.exists()) {
                throw new RuntimeException("파일을 찾을 수 없습니다: " + fileName);
            }

            String contentType = Files.probeContentType(filePath);
            if (contentType == null) {
                contentType = "application/octet-stream";
            }

            String originalFileName = getOriginalFileName(fileName);
            if (originalFileName == null) {
                originalFileName = fileName;
            }


            String encodedFileName = URLEncoder.encode(originalFileName, StandardCharsets.UTF_8)
                    .replaceAll("\\+", "%20");

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename*=UTF-8''" + encodedFileName)
                    .body(resource);

        } catch (IOException e) {
            throw new RuntimeException("파일 다운로드 중 오류가 발생했습니다.", e);
        }
    }


    private String getOriginalFileName(String storedName) {

        String originalName = attachmentMapper.findOriginalFileNameByStoredName(storedName);
        return originalName;
    }


    private String saveFile(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("빈 파일은 저장할 수 없습니다.");
        }

        String originalFilename = file.getOriginalFilename();
        String storedFilename = UUID.randomUUID().toString() + "_" + originalFilename;
        Path filePath = Paths.get(uploadDir, storedFilename);
        file.transferTo(filePath.toFile());
        return storedFilename;
    }



//    public List<Attachment> getAttachmentsByPostId(int postId) {
//        return attachmentMapper.getAttachmentsByPostId(postId);
//    }

}
