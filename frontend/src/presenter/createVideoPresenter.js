import video from "../model/Videos";
import user from "../model/Users";

class CreateVideoPresenter {

    onUploadVideo(){
        video.addVideo(video.state.newVideo.title,
            video.state.newVideo.link,
            video.state.newVideo.tags,user.state.currentUser);
        video.changeNewVideoProperty("title","");
        video.changeNewVideoProperty("link","");
        video.changeNewVideoProperty("tags","");

        window.location.assign("#/videos");
    }

    onChange(property,value){
        video.changeNewVideoProperty(property,value);
    }
}

const createVideoPresenter = new CreateVideoPresenter();

export default createVideoPresenter;