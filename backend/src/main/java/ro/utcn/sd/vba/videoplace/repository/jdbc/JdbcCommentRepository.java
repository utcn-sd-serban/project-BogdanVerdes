package ro.utcn.sd.vba.videoplace.repository.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import ro.utcn.sd.vba.videoplace.entity.Comment;
import ro.utcn.sd.vba.videoplace.repository.api.CommentRepository;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JdbcCommentRepository implements CommentRepository{
    private final JdbcTemplate template;

    @Override
    public Comment save(Comment comment) throws SQLException {
        int id = insert(comment);
        comment.setId(id);
        return comment;
    }

    @Override
    public Optional<Comment> findById(int id) {
        List<Comment> comments = template.query("SELECT * FROM Comment WHERE idComment = ?",new CommentMapper(),id);
        return comments.isEmpty() ? Optional.empty() : Optional.of(comments.get(0));
    }

    @Override
    public List<Comment> findAll() {
        return template.query("SELECT * FROM Comment",new CommentMapper());
    }

    @Override
    public List<Comment> findAllFromVideo(int videoId) {
        return template.query("SELECT * FROM Comment WHERE Video_idVideo = ?",new CommentMapper(),videoId);
    }

    private Integer insert(Comment comment){
        SimpleJdbcInsert insert = new SimpleJdbcInsert(template);
        insert.setTableName("Comment");
        insert.usingGeneratedKeyColumns("idComment");
        Map<String,Object> data = new HashMap<>();
        data.put("idComment",comment.getId());
        data.put("Username",comment.getUsername());
        data.put("Comment",comment.getComment());
        data.put("User_idUser",comment.getUserId());
        data.put("Video_idVideo",comment.getVideoId());
        return insert.executeAndReturnKey(data).intValue();
    }
}