package ro.utcn.sd.vba.videoplace.command.comment;

import lombok.AllArgsConstructor;
import ro.utcn.sd.vba.videoplace.command.Command;
import ro.utcn.sd.vba.videoplace.dto.VideoDTO;
import ro.utcn.sd.vba.videoplace.entity.Video;
import ro.utcn.sd.vba.videoplace.service.CommentService;
import ro.utcn.sd.vba.videoplace.service.VideoService;

import java.sql.SQLException;

@AllArgsConstructor
public class ListCommentsCommand implements Command {
    private CommentService commentService;
    private VideoService videoService;
    private String link;


    @Override
    public Object execute() throws SQLException {
        //VideoDTO vDTO = videoService.findById(videoId);
        //Video video = new Video(vDTO.getId(),vDTO.getTitle(),vDTO.getLink(),vDTO.getUserId(),vDTO.getRating());
        return commentService.findCommentsByVideo(link);
    }
}