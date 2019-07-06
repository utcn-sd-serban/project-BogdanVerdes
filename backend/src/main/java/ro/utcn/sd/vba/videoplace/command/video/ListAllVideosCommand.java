package ro.utcn.sd.vba.videoplace.command.video;

import lombok.AllArgsConstructor;
import ro.utcn.sd.vba.videoplace.command.Command;
import ro.utcn.sd.vba.videoplace.service.VideoService;

@AllArgsConstructor
public class ListAllVideosCommand implements Command{
    private VideoService videoService;

    @Override
    public Object execute(){
        return videoService.findAllVideos();
    }
}
