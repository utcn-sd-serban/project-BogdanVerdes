package ro.utcn.sd.vba.videoplace.repository.api;

import ro.utcn.sd.vba.videoplace.entity.Tag;

import java.sql.SQLException;
import java.util.List;

public interface TagRepository {
    Tag save(Tag tag) throws SQLException;

    List<Tag> findAll();

}

