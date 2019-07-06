import React from "react";

const CommentsList = ({ comments, onCommentVideo, onChange, newComment, link }) => (
    <div>
        <h2>Comments</h2>
        <table>
            <thead>
            
            </thead>
            <iframe width="420" height="315"
                    src={link}>
            </iframe>
            <tbody>
                {
                    
                    comments.map((comment,index) =>(                        
                        <tr key={index}>
                            <td>{comment.comment}</td>
                            <td>{comment.username}</td>
                        </tr>
                    ))
                }
            </tbody>
        </table>
        <input value={newComment}
                onChange={ e=> onChange("text",e.target.value)}/> 
        <button onClick={() => onCommentVideo(link)}>Post Comment</button>
        <br/>
        
    </div>
);

export default CommentsList;