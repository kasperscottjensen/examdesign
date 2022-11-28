class Aformhandler {

    constructor() {

    }

    saveuser() {
        let formdata = $('#saveuser-form').serializeJSON();
        ajsonconstructor.saveuser(formdata);
    }

}

const aformhandler = new Aformhandler();