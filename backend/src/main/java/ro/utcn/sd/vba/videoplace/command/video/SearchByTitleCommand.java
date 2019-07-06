package ro.utcn.sd.vba.videoplace.command.video;

import lombok.AllArgsConstructor;
import ro.utcn.sd.vba.videoplace.command.Command;
import ro.utcn.sd.vba.videoplace.service.VideoService;

@AllArgsConstructor
public class SearchByTitleCommand implements Command{
    private VideoService videoService;
    private String title;

    @Override
    public Object execute() {
        return videoService.findByTitle(title);
    }
}
