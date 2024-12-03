package board_project3_backend.repository;

import board_project3_backend.entity.Attachment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AttachmentMapper {

    void insertAttachments(List<Attachment> attachments);

    List<Attachment> getAttachmentsByPostId( Long postId);

    String findOriginalFileNameByStoredName(String storedName);
}
