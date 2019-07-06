package ro.utcn.sd.vba.videoplace.repository.jdbc;

import org.springframework.jdbc.core.RowMapper;
import ro.utcn.sd.vba.videoplace.entity.Comment;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentMapper implements RowMapper<Comment> {

    @Override
    public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Comment(rs.getInt("idComment"),
                rs.getString("Username"),
                rs.getString("Comment"),
                rs.getInt("User_idUser"),
                rs.getInt("Video_idVideo"));
    }
}
