package ro.utcn.sd.vba.videoplace.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int idTag;
    private int idVideo;
    public VideoTag(int idTag,int idVideo){
        this.id = 0;
        this.idTag = idTag;
        this.idVideo = idVideo;
    }
}
