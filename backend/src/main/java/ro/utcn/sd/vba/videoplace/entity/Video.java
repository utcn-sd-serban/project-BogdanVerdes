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
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String link;
    private int rating;
    private int userId;
    public Video(String title, String link, int userId){
        id = 0;
        this.title = title;
        this.link = link;
        this.rating = 0;
        this.userId = userId;
    }
}
