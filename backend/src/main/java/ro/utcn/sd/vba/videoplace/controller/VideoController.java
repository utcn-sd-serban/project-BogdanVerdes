package ro.utcn.sd.vba.videoplace.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.utcn.sd.vba.videoplace.command.Invoker;
import ro.utcn.sd.vba.videoplace.command.video.CreateVideoCommand;
import ro.utcn.sd.vba.videoplace.command.video.ListAllVideosCommand;
import ro.utcn.sd.vba.videoplace.command.video.SearchByTagCommand;
import ro.utcn.sd.vba.videoplace.command.video.SearchByTitleCommand;
import ro.utcn.sd.vba.videoplace.dto.VideoDTO;
import ro.utcn.sd.vba.videoplace.entity.Tag;
import ro.utcn.sd.vba.videoplace.entity.User;
import ro.utcn.sd.vba.videoplace.service.UserService;
import ro.utcn.sd.vba.videoplace.service.VideoService;
import ro.utcn.sd.vba.videoplace.service.VideoTagService;

import java.sql.SQLException;
import java.util.List;

class VideoContext{
    private String title;
    private String link;
    private String username;
    private String tags;

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getTags() {
        return tags;
    }

    public String getUsername() {
        return username;
    }

    public VideoContext(String title, String link, String username, String tags){
        this.title = title;
        this.link = link;
        this.username = username;
        this.tags = tags;
    }
}

@RestController
@RequiredArgsConstructor
public class VideoController {
    private final UserService userService;
    private final VideoService videoService;
    private final VideoTagService videoTagService;
    private final Invoker invoker;

    @GetMapping("/videos")
    public Object listAll() throws SQLException {
        invoker.setCommand(new ListAllVideosCommand(videoService));
        return invoker.invoke();
    }

    @PostMapping("/videos")
    public Object create(@RequestBody VideoContext context) throws SQLException {
        VideoDTO videoDTO = new VideoDTO();
        videoDTO.setTitle(context.getTitle());
        System.out.println(context.getLink());
        videoDTO.setLink(context.getLink());
        videoDTO.setTags(context.getTags());
        List<User> users = userService.findAllUsers();
        for(User u : users){
            if(u.getUsername().equals(context.getUsername())){
                videoDTO.setUserId(u.getId());
            }
        }
        invoker.setCommand(new CreateVideoCommand(videoService, videoDTO));
        return invoker.invoke();
    }

    @GetMapping("/videos/filterTitle={title}")
    public Object searchTitle(@PathVariable String title) throws SQLException {
        invoker.setCommand(new SearchByTitleCommand(videoService, title));
        return invoker.invoke();
    }

    @GetMapping("/videos/filterTag={tag}")
    public Object searchTag(@PathVariable Tag tag) throws SQLException {
        invoker.setCommand(new SearchByTagCommand(videoTagService, tag));
        return invoker.invoke();
    }

}