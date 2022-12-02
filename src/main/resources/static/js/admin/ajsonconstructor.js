class Ajsonconstructor {

    constructor() {

    }

    saveuser(formdata) {
        let data =
            {
                "username": formdata.username,
                "password": formdata.password,
                "enabled": formdata.enabled,
                "authority": {
                    "username": formdata.username,
                    "authority": formdata.authority
                }
            }
            aapicaller.post(JSON.stringify(data), "/user/save");
    }

    deleteuser(username) {
        let data =
            {
                "username": username
            }
        aapicaller.delete(JSON.stringify(data), "/user/delete");
    }

    async edituser(edituserformdata) {
        let edituserdata;
        if(edituserformdata.password === ""){
            edituserdata =
                {
                    "username": edituserformdata.editusername,
                    "enabled": edituserformdata.edituserenabled,
                    "authority": {
                        "username": edituserformdata.editusername,
                        "authority": edituserformdata.edituserauthority
                    }
                }
        } else {
            edituserdata =
                {
                    "username": edituserformdata.editusername,
                    "enabled": edituserformdata.edituserenabled,
                    "password": edituserformdata.edituserpassword,
                    "authority": {
                        "username": edituserformdata.editusername,
                        "authority": edituserformdata.edituserauthority
                    }
                }
        }

        aapicaller.put(JSON.stringify(edituserdata), "/user/update");
    }

}

const ajsonconstructor = new Ajsonconstructor();