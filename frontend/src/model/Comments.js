import { EventEmitter } from "events";
import { getClient } from "./Users";

class Comment extends EventEmitter{
    constructor(){
        super();
        this.state = {
            comments : [],
            newComment:{
                text: "",
                username: "",
            },
            searchedComments:[]
        }
    }

    addComment(text,username,link){
        return getClient().createComment(text, username, link)
            .then(comment => {
                
                    this.appendComment(comment);
                    //this.emit("changeComment", this.state); // the state has changed, passed the new state as arg
                
            })
    }

    changeNewCommentProperty(property,value){
            this.state = {
                ...this.state,
                newComment:{
                    ...this.state.newComment,
                    [property]:value
                }
            }
            this.emit("change",this.state);
    }
    
    listComments(link){
        return getClient().findCommentsByLink(link)
        .then(comments => {
            if (comments !== undefined) {
                this.state = {
                    ...this.state,
                    comments: comments
                };
                this.emit("change", this.state);
            }
        });
    }

    modifyTextForComment(newComment) {
        let comments = this.state.comments;
        for (let i = 0; i < comments.length; i++) {
            if (newComment.id === comments[i].id) {
                comments[i].text = newComment.text;
            }
        }
        this.state = {
            ...this.state,
            comments: comments
        };
        this.emit("change", this.state);
    }
    
}

const comment = new Comment();

export default comment;