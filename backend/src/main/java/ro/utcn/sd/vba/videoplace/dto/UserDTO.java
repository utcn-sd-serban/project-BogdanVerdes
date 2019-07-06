package ro.utcn.sd.vba.videoplace.dto;

import lombok.Data;
import ro.utcn.sd.vba.videoplace.entity.User;

@Data
public class UserDTO {

    private int id;
    private String username;
    private String password;
    private String email;
    private boolean privilege;

    public static UserDTO ofEntity(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setUsername(user.getUsername());
        userDTO.setPrivilege(user.isPrivilege());

        return userDTO;
    }
}
