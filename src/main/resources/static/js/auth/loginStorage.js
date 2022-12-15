class LoginStorage{
    constructor() {
    }
    saveUsernameToLocalStorage(){
        let username = $('#username').val();
        console.log(username);
        localStorage.setItem("username", username);
    }

    clearStorage() {
        localStorage.clear();
    }
}
const loginStorage = new LoginStorage();
