package ro.utcn.sd.vba.videoplace.repository.api;

import ro.utcn.sd.vba.videoplace.entity.Video;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface VideoRepository {
    Video save(Video video) throws SQLException;

    Optional<Video> findById(int id);

    List<Video> findAll();
}
