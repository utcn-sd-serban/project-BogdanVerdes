package ro.utcn.sd.vba.videoplace.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ro.utcn.sd.vba.videoplace.command.Invoker;
import ro.utcn.sd.vba.videoplace.command.comment.CreateCommentCommand;
import ro.utcn.sd.vba.videoplace.command.comment.ListCommentsCommand;
import ro.utcn.sd.vba.videoplace.dto.CommentDTO;
import ro.utcn.sd.vba.videoplace.entity.Video;
import ro.utcn.sd.vba.videoplace.service.CommentService;
import ro.utcn.sd.vba.videoplace.service.VideoService;

import java.sql.SQLException;

class CommentContext{
    private String comment;
    private String username;
    private String link;

    public String getComment() {
        return comment;
    }

    public String getUsername() {
        return username;
    }

    public String getLink() {
        return link;
    }

    public CommentContext(String comment, String username, String link){
        this.comment = comment;
        this.username = username;
        this.link = link;
    }
}

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final VideoService videoService;
    private final Invoker invoker;

    @GetMapping("/video/{link}/comments")
    public Object commentsOfVideo(@PathVariable("link") String link) throws SQLException {
        invoker.setCommand(new ListCommentsCommand(commentService, videoService, link));
        return invoker.invoke();
    }

    @PostMapping("/comments")
    public Object create(@RequestBody CommentContext context) throws SQLException {
        invoker.setCommand(new CreateCommentCommand(commentService, context.getComment(), context.getUsername(), context.getLink()));
        return invoker.invoke();
    }

}
