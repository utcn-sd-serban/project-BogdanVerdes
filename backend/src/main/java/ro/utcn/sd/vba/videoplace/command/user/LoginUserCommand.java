package ro.utcn.sd.vba.videoplace.command.user;

import lombok.AllArgsConstructor;
import ro.utcn.sd.vba.videoplace.command.Command;
import ro.utcn.sd.vba.videoplace.service.UserService;

@AllArgsConstructor
public class LoginUserCommand implements Command{

    private UserService userService;
    private String username;
    private String password;

    @Override
    public Object execute(){
        return userService.loginUser(username,password);
    }


}
