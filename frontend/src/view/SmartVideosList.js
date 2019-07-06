import React, {Component} from "react";
import video from "../model/Videos";
import VideosList from "./VideosList";
import videosListPresenter from "../presenter/videosListPresenter";


const mapModelStateToComponentState = modelState => ({
        videos: modelState.videos,
    }
);

export default class SmartVideosList extends Component {
    constructor(props) {
        super(props);
        this.state = mapModelStateToComponentState(video.state);
        this.listener = modelState => this.setState(mapModelStateToComponentState(modelState));
        video.addListener("change", this.listener);
        videosListPresenter.onInit();
    }

    render() {
        return (
            <VideosList
                onUploadVideo={videosListPresenter.onUploadVideo}
                onChange={videosListPresenter.onChange}
                onSearch={videosListPresenter.onSearch}
                onWatch={videosListPresenter.onWatch}
                videos={this.state.videos}/>
        );
    }

    componentWillUnmount() {
        video.removeListener("change", this.listener);
    }
}