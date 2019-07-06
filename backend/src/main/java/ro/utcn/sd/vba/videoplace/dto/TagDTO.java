package ro.utcn.sd.vba.videoplace.dto;

import lombok.Data;
import ro.utcn.sd.vba.videoplace.entity.Tag;

@Data
public class TagDTO {
    private int id;
    private String name = "";

    public static TagDTO ofEntity(Tag tag){
        TagDTO tagDTO = new TagDTO();
        tagDTO.setId(tag.getId());
        tagDTO.setName(tag.getName());

        return tagDTO;
    }
}
