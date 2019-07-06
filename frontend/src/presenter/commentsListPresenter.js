import comment from "../model/Comments";
import user from "../model/Users"

class CommentsListPresenter {
    onCommentVideo(index){
        var res = index.split("/")
        comment.addComment(comment.state.newComment.text,user.state.currentUser.username,res[4])
        comment.state.newComment.text = "";
        window.location.assign("#/watch/"+res[4]);
    }
    onChange(property,value){
        comment.changeNewCommentProperty(property,value);
    }
    onInit(link){
        comment.listComments(link);
    }  
}

const commentsListPresenter = new CommentsListPresenter();

export default commentsListPresenter;