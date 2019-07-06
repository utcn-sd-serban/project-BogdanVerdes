package ro.utcn.sd.vba.videoplace.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ro.utcn.sd.vba.videoplace.dto.VideoDTO;

@Data
@EqualsAndHashCode(callSuper = true)
public class CreateVideoEvent extends BaseEvent{
    private final VideoDTO video;

    public CreateVideoEvent(VideoDTO video) {
        super(EventType.VIDEO_CREATED);
        this.video = video;
    }
}
