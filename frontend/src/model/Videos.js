import { EventEmitter } from "events";
import  {getClient} from "./Users";

class Video extends EventEmitter{
    constructor(){
        super();
        this.state = {
            videos : [],
            newVideo:{
                title: "",
                link: "",
                tags: "",
                username: "",
                rating: 0
            },
            currentVideo:{
                link: ""  
            },
            searchedVideos: []
        }
    }

    addVideo(title,link,tags,username){
        return getClient().createVideo(title, link, username, tags)
            .then(video => {
                if (!this.videoAlreadyExists(video.id)) {
                    this.appendVideo(video);
                }


            });
    }

    appendVideo(video) {
        this.state = {
            ...this.state,
            videos: [video].concat(this.state.videos)
        };
        this.emit("change",this.state);
    }

    videoAlreadyExists(videoId) {
        let videos = this.state.videos;
        for (let i = 0; i < videos.length; i++) {
            if (videos[i].id === videoId)
                return true;
        }
        return false;
    }

    loadVideos() {
        return getClient().loadAllVideos()
        .then(videos => {
            console.log(videos);
            this.state = {
                ...this.state,
                videos: videos
            };
            this.emit("change", this.state);
        });
           
    }

    changeNewVideoProperty(property,value){
            this.state = {
                ...this.state,
                newVideo:{
                    ...this.state.newVideo,
                    [property]:value
                }
            }
            this.emit("change",this.state);
    }

    changeMainStateProperty(property, value) {
        this.state = {
            ...this.state,
            [property]: value
        };
        this.emit("change", this.state);
    }

    // remove duplicates
    searchVideo(title){
            return getClient().filterVideos("Tag",title)
                .then(tagFiltered => {
                    console.log("Tag filtered: ");
                    console.log(tagFiltered);
                    getClient().filterVideos("Title",title)
                        .then(titleFiltered => {
                            console.log("title filtered: ");
                            console.log(titleFiltered);                            
                            var names = tagFiltered.concat(titleFiltered);
                            for(var i=0; i<names.length; ++i) {
                                for(var j=i+1; j<names.length; ++j) {
                                    if(names[i] === names[j])
                                        names.splice(j--, 1);
                                }
                            }
                            let unique = [...new Set(names)];
                            this.state.searchedVideos = unique;
                            console.log(this.state.searchedVideos);
                            //let unique = [...new Set(this.state.searchedVideos)];
                            //this.state.searchedVideos = unique;
                            this.emit("change", this.state); 
                        })
                        
                });
    }
}

const video = new Video();

export default video;