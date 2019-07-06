import React, {Component} from "react";
import video from "../model/Videos";
import CreateVideo from "./CreateVideo";
import createVideoPresenter from "../presenter/createVideoPresenter";

const mapModelStateToComponentState = modelState => ({
    title: modelState.newVideo.title,
    link: modelState.newVideo.link,
    tags: modelState.newVideo.tags
});

export default class SmartCreateVideo extends Component{
    constructor(){
        super();
        this.state = mapModelStateToComponentState(video.state);
        this.listener = modelState => this.setState(mapModelStateToComponentState(modelState));
        video.addListener("change",this.listener);
    }

    render(){
        return(
            <CreateVideo 
            onUploadVideo={createVideoPresenter.onUploadVideo}
            onChange={createVideoPresenter.onChange}
            title={this.state.title}
            link={this.state.link}
            tags={this.state.tags}/>
        );
    }

    componentWillUnmount(){
        video.removeListener("change",this.listener);
    }
}