import React, {Component} from "react";
import video from "../model/Videos";
import SearchVideo from "./SearchVideo";


const mapModelStateToComponentState = modelState => ({
    searchedVideos: modelState.searchedVideos
});

export default class SmartSearchVideo extends Component{
    constructor(){
        super();
        this.state = mapModelStateToComponentState(video.state);
        this.listener = modelState => this.setState(mapModelStateToComponentState(modelState));
        video.addListener("change",this.listener);
    }

    render(){
        return(
            <SearchVideo
            videos={this.state.searchedVideos} />
        );
    }

    componentWillUnmount(){
        video.removeListener("change",this.listener);
    }
}