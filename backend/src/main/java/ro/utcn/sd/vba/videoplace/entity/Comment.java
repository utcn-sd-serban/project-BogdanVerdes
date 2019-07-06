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
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String comment;
    private int userId;
    private int videoId;
    public Comment(String comment,String username,int userId,int videoId){
        this.id = 0;
        this.comment = comment;
        this.username = username;
        this.userId = userId;
        this.videoId = videoId;
    }
}
