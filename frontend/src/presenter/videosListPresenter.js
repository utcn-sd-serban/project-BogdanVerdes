import video from "../model/Videos";
import user from "../model/Users";
import comment from "../model/Comments";

class VideosListPresenter {
    onUploadVideo(){
        window.location.assign("#/upload-video");
    }
    onChange(property,value){
        video.changeNewVideoProperty(property,value);
    }
    onSearch(){
        video.searchVideo(video.state.newVideo.title)
            .then(() => window.location.assign("#/search"));

    }
    onWatch(index){
        video.state.currentVideo.link = index;
        comment.listComments(index);

        window.location.assign("#/watch/"+index); 
    }
    onLogout(){
        user.logout();
        window.location.assign("#/");
    }
    onInit(){
        video.loadVideos();
    }
}

const videosListPresenter = new VideosListPresenter();

export default videosListPresenter;