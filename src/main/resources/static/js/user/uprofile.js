class Uprofile{
    constructor() {
    }
    async getinfo(){
        let uapicaller = new Uapicaller();
        let username = localStorage.getItem("username");
        let profileInfo = await uapicaller.get("/profiles/find/" + username);
        console.log(profileInfo);
        console.log(profileInfo.fullName);
        document.getElementById("fullName").innerHTML = profileInfo.fullName;
        document.getElementById("phonenumber").innerHTML = profileInfo.phone;
        document.getElementById("email").innerHTML = profileInfo.email;
        document.getElementById("hiringdate").innerHTML = profileInfo.hired;




    }
}
const uprofile = new Uprofile();