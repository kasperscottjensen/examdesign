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

    edituser(formdata) {
        console.log("HELLO FROM JOSNCONSTRUCTOR");
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
        aapicaller.put(JSON.stringify(data), "/user/update");
    }

}

const ajsonconstructor = new Ajsonconstructor();