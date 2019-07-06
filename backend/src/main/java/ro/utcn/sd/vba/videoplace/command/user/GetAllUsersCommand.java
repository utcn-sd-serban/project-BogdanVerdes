package ro.utcn.sd.vba.videoplace.command.user;

import lombok.AllArgsConstructor;
import ro.utcn.sd.vba.videoplace.command.Command;
import ro.utcn.sd.vba.videoplace.service.UserService;

@AllArgsConstructor
public class GetAllUsersCommand implements Command{
    private UserService userService;

    @Override
    public Object execute(){
        return userService.findAllUsers();
    }
}
