package ro.utcn.sd.vba.videoplace.repository.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import ro.utcn.sd.vba.videoplace.entity.Video;
import ro.utcn.sd.vba.videoplace.entity.VideoTag;
import ro.utcn.sd.vba.videoplace.entity.Tag;
import ro.utcn.sd.vba.videoplace.repository.api.VideoTagRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JdbcVideoTagRepository implements VideoTagRepository{
    private final JdbcTemplate template;

    @Override
    public VideoTag save(VideoTag videoTag) throws SQLException {
        if(videoTag.getId() ==0){

            int id = insert(videoTag);
            videoTag.setId(id);
        }
        return videoTag;
    }

    @Override
    public List<Video> findVideosByTag(String tag, List<Video> videoList) {
        return template.query("SELECT Video.* FROM Video INNER JOIN video_has_tag thq " +
                "ON Video.idVideo = thq.Video_idVideo " +
                "INNER JOIN Tags ON thq.Tags_Name = Tags.Name AND Tags.Name = ? ",new VideoMapper(),tag);
    }

    @Override
    public List<Tag> findTagsByVideo(Video video, List<Tag> allTags) {
        return template.query("SELECT Tag.* FROM Tag INNER JOIN Video_has_Tag vht ON vht.Tag_idTag = Tag.idTag" +
                        " INNER JOIN Video ON Video.idVideo = vht.Video_idVideo AND Video.idVideo = ? ",
                new TagMapper(),video.getId());
    }
    private Integer insert(VideoTag videoTag){
        SimpleJdbcInsert insert = new SimpleJdbcInsert(template);
        insert.setTableName("video_has_tag");
        insert.usingGeneratedKeyColumns("idVideoTag");
        Map<String,Object> data = new HashMap<>();
        data.put("Tag_idTag",videoTag.getIdTag());
        data.put("Video_idVideo",videoTag.getIdVideo());
        return insert.executeAndReturnKey(data).intValue();

    }

    @Override
    public List<VideoTag> findAll() {
        return new ArrayList<>();
    }
}
