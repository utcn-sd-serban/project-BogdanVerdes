package ro.utcn.sd.vba.videoplace.repository.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import ro.utcn.sd.vba.videoplace.entity.User;
import ro.utcn.sd.vba.videoplace.repository.api.UserRepository;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JdbcUserRepository implements UserRepository{
    private final JdbcTemplate template;

    @Override
    public User save(User user) throws SQLException {

        String name = insert(user);
        user.setUsername(name);
        return user;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        List<User> users = template.query("SELECT * FROM User WHERE Username = ?",new UserMapper(),username);
        System.out.println(users.get(0).getId());
        return users.isEmpty() ? Optional.empty() : Optional.of(users.get(0));
    }

    @Override
    public List<User> findAll() {
        return template.query("SELECT * FROM User",new UserMapper());
    }

    private String insert(User user){
        SimpleJdbcInsert insert = new SimpleJdbcInsert(template);
        insert.setTableName("User");
        insert.usingGeneratedKeyColumns("idUser");
        Map<String,Object> data = new HashMap<>();
        data.put("idUser",user.getId());
        data.put("Username",user.getUsername());
        data.put("Password",user.getPassword());
        data.put("Privilege",user.isPrivilege());
        data.put("Email",user.getEmail());
        insert.executeAndReturnKey(data).intValue();
        return user.getUsername();

    }

}
