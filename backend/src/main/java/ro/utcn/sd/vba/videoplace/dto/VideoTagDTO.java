package ro.utcn.sd.vba.videoplace.dto;

import lombok.Data;
import ro.utcn.sd.vba.videoplace.entity.VideoTag;

@Data
public class VideoTagDTO {
    private int id;
    private int idTag;
    private int idVideo;
    public static VideoTagDTO ofEntity(VideoTag videoTag){
        VideoTagDTO videoTagDTO = new VideoTagDTO();
        videoTagDTO.setId(videoTag.getId());
        videoTagDTO.setIdTag(videoTag.getIdTag());
        videoTagDTO.setIdVideo(videoTag.getIdVideo());
        return videoTagDTO;
    }
}
