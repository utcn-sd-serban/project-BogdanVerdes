import React from "react";

const SearchVideo = ({ videos }) => (
    <div>
        <h2>Videos</h2>
        <table>
            <thead>
            </thead>
            <tbody>
                {
                    videos.map((video,index) =>(
                        <tr key={index}>
                            <td>{video.title}</td>
                            <td>{video.username}</td>
                            <td>{video.rating}</td>
                        </tr>
                    ))
                }
            </tbody>
        </table>
    </div>
);

export default SearchVideo;