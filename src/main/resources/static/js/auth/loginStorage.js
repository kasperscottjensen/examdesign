class LoginStorage{
    constructor() {
    }
    saveUsernameToLocalStorage(){
        let username = $('#username').val();
        console.log(username);
        localStorage.setItem("username", username);
    }
}
const loginStorage = new LoginStorage();
