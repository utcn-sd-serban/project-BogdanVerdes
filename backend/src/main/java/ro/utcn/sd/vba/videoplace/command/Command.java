package ro.utcn.sd.vba.videoplace.command;

import java.sql.SQLException;

public interface Command {
    Object execute() throws SQLException;
}