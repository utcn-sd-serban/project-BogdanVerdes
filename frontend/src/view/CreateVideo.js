import React from "react";

const CreateVideo = ({ newTitle, newLink, newTags,onChange, onUploadVideo }) => (
    <div>
        <h2>Upload video:</h2>
        <div>
            <label>Title: </label>
            <input value={newTitle} 
                onChange={ e=> onChange("title",e.target.value)}/>
            <br />
            <label>Link:</label>
            <input value={newLink} 
                onChange={ e=> onChange("link",e.target.value)}/>
            <br />
            <label>Tags: </label>
            <input value={newTags} 
                onChange={ e=> onChange("tags",e.target.value)}/>
            <br />
            <button onClick={onUploadVideo}>Upload Video</button>
        </div>
    </div>
);

export default CreateVideo;