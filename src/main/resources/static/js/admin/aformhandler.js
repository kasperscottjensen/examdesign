class Aformhandler {

    constructor() {

    }

    saveuser() {
        let formdata = $('#saveuser-form').serializeJSON();
        ajsonconstructor.saveuser(formdata);
    }

    edituser() {
        console.log("HELLO FROM FORMHANDLER");
        console.log("HELLO FROM FORMHANDLER");
        console.log("HELLO FROM FORMHANDLER");
        console.log("HELLO FROM FORMHANDLER");
        let formdata = document.getElementById("modal-user-edit").serializeJSON();
        ajsonconstructor.edituser(formdata);
    }

}

const aformhandler = new Aformhandler();