package ro.utcn.sd.vba.videoplace.command.comment;

import lombok.AllArgsConstructor;
import ro.utcn.sd.vba.videoplace.command.Command;
import ro.utcn.sd.vba.videoplace.dto.CommentDTO;
import ro.utcn.sd.vba.videoplace.service.CommentService;

@AllArgsConstructor
public class CreateCommentCommand implements Command {
    private CommentService commentService;
    private String comment;
    private String username;
    private String link;

    @Override
    public Object execute() {
        try {
            return commentService.saveComment(comment,username,link);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

