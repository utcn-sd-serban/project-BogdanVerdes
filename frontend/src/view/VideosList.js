import React from "react";

const VideosList = ({ videos, onSearch, onUploadVideo, onWatch, onChange, searchTitle, }) => (
    <div>
        <h2>Videos</h2>
        <table>
            <thead>
            <input value={searchTitle} 
                onChange={ e=> onChange("title",e.target.value)}/>
            <button onClick={onSearch}>Search</button>
            </thead>
            <tbody>
                {
                    videos.map((video,index) =>(
                        <tr key={index}>
                            <td>{video.title}</td>
                            <td>{video.username}</td>
                            <td>{video.rating}</td>
                            <td><button onClick={() => onWatch(video.link)}>Watch video</button></td>
                        </tr>
                    ))
                }
            </tbody>
        </table>
        <button onClick={onUploadVideo}>Upload Video</button>
        <br/>
        
    </div>
);

export default VideosList;