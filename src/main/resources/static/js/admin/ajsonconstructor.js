class Ajsonconstructor {

    constructor() {

    }

    saveshift(formdata) {
        let shiftData;
        if(formdata.addshifttimeend === ""){
            shiftData = {
                "date": formdata.addshiftdate,
                "timeStart": formdata.addshifttimestart,
                "user":
                    {
                        "username": formdata.addshiftusername
                    }
            }
        }else{
            shiftData = {
                "date": formdata.addshiftdate,
                "timeStart": formdata.addshifttimestart,
                "timeEnd": formdata.addshifttimeend,
                "user":
                    {
                        "username": formdata.addshiftusername
                    }
            }
        }
        aapicaller.postNoReload(JSON.stringify(shiftData), "/shift/save");
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

    deleteShift(shiftId) {
        let data =
            {
                "id": shiftId
            }
        aapicaller.deleteNoReload(JSON.stringify(data), "/shift/delete");
    }

}

const ajsonconstructor = new Ajsonconstructor();