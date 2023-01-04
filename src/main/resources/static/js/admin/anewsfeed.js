class Anewsfeed{
    constructor() {

    }

    async getallposts(){
        let apicaller = new Uapicaller();
        let newsfeedposts = await apicaller.get("/newsfeed/findAll")
        console.log(newsfeedposts);
        localStorage.setItem("newsfeedArray", JSON.stringify(newsfeedposts));
        console.log(newsfeedposts);
        for(let index in newsfeedposts){
            let newsfeedentry = newsfeedposts[index];
            let dateofpost = newsfeedentry.date;
            const [year, month, day] = dateofpost.split('-');
            const date = [day, month, year].join('/');
            newsfeedentry.date = date;
            let idTag = "idpost-" + newsfeedentry.id;
            let newsfeedtargetcard = $('#newsfeedcards');
            let newsfeedcards =
                `<div class="col-6 mb-4">
                                <div class="card border border-dark">
                                    <div class="card-body p-0">
                                        <!--View Post-->
                                        <div class="d-flex justify-content-end mt-3 mx-4">
                                            <i id="deleteposticon" class="fa-solid fa-trash fs-4" ondblclick="anewsfeed.deletemodal()"></i>
                                        </div>
                                        <div class="mx-4">
                                            <h4 class="fw-bold mt-2" id="titlepost">${newsfeedentry.title}</h4>
                                        </div>
                                        <div class="mx-4 mt-4">
                                            <p id="bodypost" class="fs-5" style="max-height: 250px; overflow-y: scroll;">${newsfeedentry.body}</p>
                                        </div>
                                        <div class="d-flex justify-content-between mx-4">
                                            <p id="'+idTag+'" class="fs-3 mt-4">${newsfeedentry.id}</p>
                                            <p id="datepost" class="fs-6 fw-light mt-5">${newsfeedentry.date}</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>`;
            newsfeedtargetcard.append(newsfeedcards);

        }
    }
    deletemodal(){
        $('#deletemodal').modal('show');
        $('#deletepost').clear();
        let body = document.getElementById("bodypost");
        let title = document.getElementById("titlepost");
        let date = document.getElementById("datepost");
        let id = document.getElementById("namepost");
        let newsfeedtargetcard = $('deletepost');
        let newsfeedcards =
            `<div class="mb-4">
                            <div class="card border border-dark">
                                <div class="card-body p-0">
                                    <!--View Post-->
                                    <div class="mx-4">
                                        <h4 class="fw-bold mt-5" id="titlepostdelete">${title}</h4>
                                    </div>
                                    <div class="mx-4 mt-4">
                                        <p id="bodypost" class="fs-5" style="max-height: 250px; overflow-y: scroll;">${body}</p>
                                    </div>
                                    <div class="d-flex justify-content-between mx-4">
                                        <p id="idpostdelete" class="fs-3 mt-4">${id}</p>
                                        <p id="datepostdelete" class="fs-6 fw-light mt-5">${date}</p>
                                    </div>
                                </div>
                            </div>
                        </div>`;
        newsfeedtargetcard.append(newsfeedcards);
    }
    deletepostaccept(){
        let apicaller = new Uapicaller();
        let id = document.getElementById("idpostdelete");
        apicaller.deleteById("/newsfeed/delete/" + id);
    }
}
const anewsfeed = new Anewsfeed();