package ro.utcn.sd.vba.videoplace.repository.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import ro.utcn.sd.vba.videoplace.entity.Tag;
import ro.utcn.sd.vba.videoplace.repository.api.TagRepository;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JdbcTagRepository implements TagRepository {
    private final JdbcTemplate template;

    @Override
    public Tag save(Tag tag) throws SQLException {
        if (!tag.getName().equals("")) {
            int id = insert(tag);
            tag.setId(id);
        }
        return tag;
    }

    @Override
    public List<Tag> findAll() {
        return template.query("SELECT * FROM Tag", new TagMapper());

    }

    private Integer insert(Tag tag) {

        SimpleJdbcInsert insert = new SimpleJdbcInsert(template);
        insert.setTableName("Tag");
        insert.setGeneratedKeyName("idTag");
        Map<String, Object> data = new HashMap<>();
        data.put("idTag", tag.getId());
        data.put("Name", tag.getName());
        //return insert.executeAndReturnKey(data).toString();
        return insert.executeAndReturnKey(data).intValue();

    }
}
