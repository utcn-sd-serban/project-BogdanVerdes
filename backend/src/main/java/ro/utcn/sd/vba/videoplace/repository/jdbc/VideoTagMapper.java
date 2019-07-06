package ro.utcn.sd.vba.videoplace.repository.jdbc;

import org.springframework.jdbc.core.RowMapper;
import ro.utcn.sd.vba.videoplace.entity.VideoTag;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VideoTagMapper implements RowMapper<VideoTag> {

    @Override
    public VideoTag mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new VideoTag(rs.getInt("idVideoTag"),
                rs.getInt("Video_idVideo"),
                rs.getInt("Tag_idTag"));
    }
}

