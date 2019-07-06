import React, {Component} from "react";
import video from "../model/Videos";
import comment from "../model/Comments";
import CommentsList from "./CommentsList";
import commentsListPresenter from "../presenter/commentsListPresenter";


const mapModelStateToComponentState = modelState => ({
    comments: modelState.comments,
}
);

export default class SmartCommentsList extends Component{
    constructor(){
        super();
        this.state = mapModelStateToComponentState(comment.state);
        this.listener = modelState => this.setState(mapModelStateToComponentState(modelState));
        comment.addListener("change",this.listener);
        //commentsListPresenter.onInit(this.state.comments[0].vId);
    }

    render(){
        return(
            <CommentsList 
            comments = {this.state.comments}
            link = {"https://www.youtube.com/embed/"+video.state.currentVideo.link}
            onCommentVideo = {commentsListPresenter.onCommentVideo}
            onChange = {commentsListPresenter.onChange}
           />
        );
    }

    componentWillUnmount(){
        comment.removeListener("change",this.listener);
    }
}