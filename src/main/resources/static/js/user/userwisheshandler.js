class Userwisheshandler{
    constructor() {
    }

    async getWishForUser(){
        let username = localStorage.getItem("username");
        let userWishes = await uapicaller.get("/wishes/findAllByUser/" + username);
        console.log(userWishes);
        localStorage.setItem("wishesArray", JSON.stringify(userWishes));
    }
    updateModal(month, year){
        let getMonth = month + 1;
        document.getElementById("showUsername").innerHTML = localStorage.getItem("username");
        var rows = document.getElementsByTagName('tr');
        Array.from(rows).forEach(function(tr) {
            tr.addEventListener('dblclick', function(event) {
                document.getElementById("current-date").innerHTML = 0 + event.target.textContent + "/" + getMonth + "/" + year;
            });
        });
        this.constructWishOnDate();

    }
    constructWishOnDate(){
        $('#larry').children().remove();
        console.log("kom")
        let getWishes = JSON.parse(localStorage.getItem("wishesArray"));
        let currentDate = document.getElementById("current-date").innerHTML.valueOf();
        console.log(currentDate);
        const [day, month, year] = currentDate.split('/');
        const result = [year, month, day].join('-');
        console.log(result);
        for (let index in getWishes) {
            if (getWishes[index].date === result) {
                let wishesEntry = getWishes[index];
                let wishesCardsTarget = $('#larry');
                let wishesCards =
                    `<tr>
                            <td>
                                ${wishesEntry.wish}
                            </td>
                        </tr>`;
                //Append to html
                wishesCardsTarget.append(wishesCards);
            }
        }
    }

}
const userwisheshandler = new Userwisheshandler();

//Du skal have user objekt med. Du skal hente hele user objekt for at connecte user til wishes. Admin har kun adgang til userController
//Hvordan bybasser jeg det?