package ro.utcn.sd.vba.videoplace.repository.api;

import ro.utcn.sd.vba.videoplace.entity.Tag;
import ro.utcn.sd.vba.videoplace.entity.Video;
import ro.utcn.sd.vba.videoplace.entity.VideoTag;

import java.sql.SQLException;
import java.util.List;

public interface VideoTagRepository {
    VideoTag save(VideoTag videoTag) throws SQLException;
    List<Video> findVideosByTag(String tag, List<Video> videoList);
    List<Tag> findTagsByVideo(Video video, List<Tag> allTags);
    List<VideoTag> findAll();
}
