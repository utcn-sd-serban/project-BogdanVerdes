package ro.utcn.sd.vba.videoplace.repository.jdbc;

import org.springframework.jdbc.core.RowMapper;
import ro.utcn.sd.vba.videoplace.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new User(rs.getInt("idUser"),
                rs.getString("Username"),
                rs.getString("Password"),
                rs.getString("Email"),
                rs.getBoolean("Privilege")
        );
    }
}