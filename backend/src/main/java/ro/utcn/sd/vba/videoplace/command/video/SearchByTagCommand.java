package ro.utcn.sd.vba.videoplace.command.video;

import lombok.AllArgsConstructor;
import ro.utcn.sd.vba.videoplace.command.Command;
import ro.utcn.sd.vba.videoplace.entity.Tag;
import ro.utcn.sd.vba.videoplace.service.VideoTagService;

@AllArgsConstructor
public class SearchByTagCommand implements Command{
    private VideoTagService videoTagService;
    private Tag tag;

    @Override
    public Object execute(){
        return videoTagService.findVideosByTag(tag);
    }
}
