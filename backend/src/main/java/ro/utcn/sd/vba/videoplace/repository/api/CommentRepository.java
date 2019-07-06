package ro.utcn.sd.vba.videoplace.repository.api;

import ro.utcn.sd.vba.videoplace.entity.Comment;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CommentRepository {
    Comment save(Comment comment) throws SQLException;

    Optional<Comment> findById(int id);

    List<Comment> findAll();

    List<Comment> findAllFromVideo(int videoId);
}

