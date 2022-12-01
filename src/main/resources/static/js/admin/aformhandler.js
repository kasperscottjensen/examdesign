class Aformhandler {

    constructor() {

    }

    saveuser() {
        let formdata = $('#saveuser-form').serializeJSON();
        ajsonconstructor.saveuser(formdata);
    }

    edituser() {
        event.preventDefault();
        let formdata = $('#modal-user-edit').serializeJSON();
        ajsonconstructor.edituser(formdata);
    }

}

const aformhandler = new Aformhandler();