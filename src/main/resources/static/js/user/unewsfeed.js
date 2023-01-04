class Unewsfeed{
    constructor() {
    }
    async getallposts(){
        let uapicaller = new Uapicaller();
        let newsfeedposts = await uapicaller.get("/newsfeed/findAll")
        console.log(newsfeedposts);
        localStorage.setItem("newsfeedArray", JSON.stringify(newsfeedposts));
        console.log(newsfeedposts);
        for(let index in newsfeedposts){
            let newsfeedentry = newsfeedposts[index];
            let dateofpost = newsfeedentry.date;
            const [year, month, day] = dateofpost.split('-');
            const date = [day, month, year].join('/');
            newsfeedentry.date = date;
            let newsfeedtargetcard = $('#newsfeedcards');
            let newsfeedcards =
                `<div class="col-6 mb-4">
                            <div class="card border border-dark">
                                <div class="card-body p-0">
                                    <!--View Post-->
                                    <div class="mx-4">
                                        <h4 class="fw-bold mt-5" id="titlepost">${newsfeedentry.title}</h4>
                                    </div>
                                    <div class="mx-4 mt-4">
                                        <p id="bodypost" class="fs-5" style="max-height: 250px; overflow-y: scroll;">${newsfeedentry.body}</p>
                                    </div>
                                    <div class="d-flex justify-content-between mx-4">
                                        <p id="namepost" class="fs-3 mt-4">${newsfeedentry.id}</p>
                                        <p id="datepost" class="fs-6 fw-light mt-5">${newsfeedentry.date}</p>
                                    </div>
                                </div>
                            </div>
                        </div>`;
            newsfeedtargetcard.append(newsfeedcards);

        }
    }
    makeposts(){
        let newsfeedposts = JSON.parse(localStorage.getItem("wishesArray"));

    }
}
const unewsfeed = new Unewsfeed();