package ro.utcn.sd.vba.videoplace.dto;

import lombok.Data;
import ro.utcn.sd.vba.videoplace.entity.Comment;

@Data
public class CommentDTO {
    private int id;
    private String username;
    private String comment;
    private int userId;
    private int videoId;
    public static CommentDTO ofEntity(Comment comment){
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setComment(comment.getComment());
        commentDTO.setUsername(comment.getUsername());
        commentDTO.setVideoId(comment.getVideoId());
        commentDTO.setUserId(comment.getUserId());
        return commentDTO;
    }
}
