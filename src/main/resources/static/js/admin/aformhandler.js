class Aformhandler {

    constructor() {

    }

    saveshift() {
        event.preventDefault();
        let formdata = $('#modal-form-addshift').serializeJSON();
        ajsonconstructor.saveshift(formdata);
        document.getElementById('modal-form-addshift').reset();
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