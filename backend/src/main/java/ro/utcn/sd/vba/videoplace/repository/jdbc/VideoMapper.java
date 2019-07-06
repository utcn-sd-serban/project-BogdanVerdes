package ro.utcn.sd.vba.videoplace.repository.jdbc;

import org.springframework.jdbc.core.RowMapper;
import ro.utcn.sd.vba.videoplace.entity.Video;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VideoMapper implements RowMapper<Video> {

    @Override
    public Video mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Video(rs.getInt("idVideo"),
                rs.getString("Title"),
                rs.getString("Link"),
                rs.getInt("Rating"),
                rs.getInt("User_idUser"));
    }
}
