package ro.utcn.sd.vba.videoplace.command.video;

import lombok.AllArgsConstructor;
import ro.utcn.sd.vba.videoplace.command.Command;
import ro.utcn.sd.vba.videoplace.dto.VideoDTO;
import ro.utcn.sd.vba.videoplace.service.VideoService;

import java.sql.SQLException;

@AllArgsConstructor
public class CreateVideoCommand implements Command{
    private VideoService videoService;
    private VideoDTO videoDTO;

    @Override
    public Object execute() {
        try {
            return videoService.saveVideo(videoDTO);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}