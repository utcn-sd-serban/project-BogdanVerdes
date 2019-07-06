import user from "../model/Users";

class VideosListPresenter {
    onLogin(){
        user.state.currentUser = user.state.newUser.username;
        user.loginUser(user.state.newUser.username,user.state.newUser.password)
        .then(()=>{
            user.searchUser(user.state.newUser.username,user.state.newUser.password);
            window.location.assign("#/videos");
        
        }
        );
    }
    onRegister(){
        user.state.currentUser = user.state.newUser.username;
        user.registerUser(user.state.newUser.username,user.state.newUser.password)
        .then(()=> { 
            user.addUser(user.state.newUser.username,user.state.newUser.password);
            window.location.assign("#/videos");
        });        
        
    }
    onChange(property,value){
        user.changeNewUserProperty(property,value);
    }

    onInit(){
        user.loadUsers();
    }
}

const videosListPresenter = new VideosListPresenter();

export default videosListPresenter;