package ro.utcn.sd.vba.videoplace.dto;

import lombok.Data;
import ro.utcn.sd.vba.videoplace.entity.Video;

@Data
public class VideoDTO {
    private int id;
    private String title;
    private String link;
    private int rating;
    private int userId;
    private String tags;
    public static VideoDTO ofEntity(Video video){
        VideoDTO videoDTO = new VideoDTO();
        videoDTO.setId(video.getId());
        videoDTO.setLink(video.getLink());
        videoDTO.setTitle(video.getTitle());
        videoDTO.setRating(video.getRating());
        videoDTO.setUserId(video.getUserId());
        return videoDTO;
    }
}
