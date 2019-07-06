const BASE_URL = "http://localhost:8080";

export default class RestClient {
    constructor(username, password) {
        this.authorization = "Basic " + btoa(username + ":" + password);
    }

    loadAllUsers = () => {
        return fetch(BASE_URL + "/users", {
            method: "GET",
            headers: {
            }
        }).then(response => response.json());
    };

    createUser = (username, password) => {
        
        return fetch(BASE_URL + "/register", {
            method: "POST",
            body: JSON.stringify({
                username: username,
                password: password,
                email : "dummy"
            }),
            headers: {
                "Content-type": "application/json"
            }
        }).then(response => response.json());
    };

    loginUser = (username, password) => {
        return fetch(BASE_URL + "/login", {
            method: "POST",
            body: JSON.stringify({
                username: username,
                password: password
            }),
            headers: {
                "Content-type": "application/json"
            }
        });
    };

    loadAllVideos = () => {
        return fetch(BASE_URL + "/videos", {
            method: "GET",
            headers: {
                "Authorization": this.authorization
            }
        }).then(response => response.json())
            .catch(() => {
                alert("Unknown user");
                window.location.assign("#/");
            });
    };

    createVideo = (title, link, username, tags) => {
        return fetch(BASE_URL + "/videos", {
            method: "POST",
            body: JSON.stringify({
                
                title: title,
                link: link,
                username: username,
                tags: tags
            }),
            headers: {
                "Authorization": this.authorization,
                "Content-type": "application/json"
            }
        }).then(response => response.json());
    };

    filterVideos = (filterType, filterText) => {
        return fetch(BASE_URL + "/videos/filter" + filterType + "=" + filterText, {
            method: "GET",
            headers: {
                "Authorization": this.authorization
            }
        }).then(response => response.json())
            .catch(() => {
                alert("Filter not working");
                window.location.assign("#/");
            });
    };

    findCommentsByLink = (link) => {
        //videoId = parseInt(videoId);
        return fetch(BASE_URL + "/video/" + link + "/comments", {
            method: "GET",
            headers: {
                "Authorization": this.authorization
            }
        }).then(response => response.json())
            .catch(() => {
                alert("Unknown user");
                window.location.assign("#/");
            });
    };

    createComment = (comment, username, link) => {
        return fetch(BASE_URL + "/comments", {
            method: "POST",
            body: JSON.stringify({
                comment: comment,
                username: username, 
                link: link
            }),
            headers: {
                "Authorization": this.authorization,
                "Content-type": "application/json"
            }
        }).then(response => response.json());
    };

}