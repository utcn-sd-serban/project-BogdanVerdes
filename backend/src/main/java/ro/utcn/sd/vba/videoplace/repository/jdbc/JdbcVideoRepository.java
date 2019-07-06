package ro.utcn.sd.vba.videoplace.repository.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import ro.utcn.sd.vba.videoplace.entity.Video;
import ro.utcn.sd.vba.videoplace.repository.api.VideoRepository;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JdbcVideoRepository implements VideoRepository{
    private final JdbcTemplate template;

    @Override
    public Video save(Video video) throws SQLException {
        int id = insert(video);
        video.setId(id);
        return video;
    }

    @Override
    public Optional<Video> findById(int id) {
        List<Video> videos = template.query("SELECT * FROM Video WHERE idVideo = ?",new VideoMapper(),id);
        return videos.isEmpty() ? Optional.empty() : Optional.of(videos.get(0));
    }

    @Override
    public List<Video> findAll() {
        return template.query("SELECT * FROM Video",new VideoMapper());
    }

    private Integer insert(Video video){
        SimpleJdbcInsert insert = new SimpleJdbcInsert(template);
        insert.setTableName("Video");
        insert.usingGeneratedKeyColumns("idVideo");
        Map<String,Object> data = new HashMap<>();
        data.put("idVideo",video.getId());
        data.put("Title",video.getTitle());
        data.put("Link",video.getLink());
        data.put("Rating",video.getRating());
        data.put("User_idUser",video.getUserId());
        return insert.executeAndReturnKey(data).intValue();

    }
}
