const todaysDate = new Date();

class AshiftOverwiev2 {

    constructor() {

    }

    async cardGenerator() {

        const target = document.getElementById("shift-cards");
        let cards = "";

        let response = await fetch("http://localhost:8080/user/api/shift/findall", { method: 'GET'});
        let shifts = await response.json();

        let todaysDateFormatted = todaysDate.getFullYear() + "-" + (todaysDate.getMonth()+1) + "-" + todaysDate.getDate();

        for(let shiftindex in shifts){
            let shiftEntry = shifts[shiftindex];
        //Checks if the shift belongs to the user and if it older than to days date
            if(shiftEntry.username.toLowerCase() === localStorage.getItem("username").toLowerCase() && shiftEntry.shift.date >= todaysDateFormatted){
                let date = new Date(shiftEntry.shift.date);
                let shiftEntryDay = '';
                if(date.getDay() === 0){
                    shiftEntryDay = "Søndag"
                }else if(date.getDay() === 1){
                    shiftEntryDay = "Mandag"
                }else if(date.getDay() === 2){
                    shiftEntryDay = "Tirsdag"
                }else if(date.getDay() === 3){
                    shiftEntryDay = "Onsdag"
                }else if(date.getDay() === 4){
                    shiftEntryDay = "Torsdag"
                }else if(date.getDay() === 5){
                    shiftEntryDay = "Fredag"
                }else {
                    shiftEntryDay = "Lørdag"
                }

                let shiftEntryLocalDate = date.toLocaleDateString().replaceAll(".", "-");

                cards +=`
                <div class="card m-2">
                  <div class="card-body">
                      <div class="row d-flex align-items-center text-center">
                          <div class="col-5 border-end border-dark">
                              <p class="fw-bold fs-4 m-0">${shiftEntry.username}</p>
                          </div>
                          <div class="col-7 d-flex justify-content-around text-start ">
                              <div>
                                  <p class=" fs-5 m-0">${shiftEntry.shift.timeStart} - ${shiftEntry.shift.timeEnd}</p>
                                  <p class=" fs-5 m-0">${shiftEntryDay} d. ${shiftEntryLocalDate}</p>
                              </div>
                              <div class="align-items-center d-flex">
                                  <i class="fa-solid fa-comment fa-2xl"></i>
                              </div>
                          </div>
                      </div>
                  </div>
                </div>
                `;
            }
        };
        target.innerHTML = cards;
    };

    async todaysShiftsCardGenerator() {

        const target = document.getElementById("todays-shift-cards-html-target");
        const cardsDay = document.getElementById("shift-today-month-and-year").textContent;
        let textArray = cardsDay.split('-');
        let cardsDayFormatted = textArray[2] + '-' + textArray[1] + '-' + textArray[0];
        let cards = '';

        let response = await fetch("http://localhost:8080/user/api/shift/findall", { method: 'GET'});
        let shifts = await response.json();

        let changeSide = true;
        let rowCounter = 0;

        for(let shiftindex in shifts){
            let shiftEntry = shifts[shiftindex];
            if(shiftEntry.shift.date === cardsDayFormatted){

                if(changeSide === true &&  (rowCounter % 2 === 0)){
                    cards += '<div class="row">';
                    changeSide = false;
                }

                cards +=`
            <div class="card m-2 col-6 d-flex justify-content-center" style="max-width: 421px;">
              <div class="card-body">
                  <div class="row d-flex align-items-center text-center">
                      <div class="col-6 ">
                          <p class="fw-bold fs-4 m-0">${shiftEntry.username}</p>
                      </div>
                      <div class="col-6 d-flex justify-content-around text-start ">
                          <div>
                              <p class=" fs-5 m-0">${shiftEntry.shift.timeStart} - ${shiftEntry.shift.timeEnd}</p>
                          </div>
                      </div>
                  </div>
              </div>
            </div>
            `;
                if(changeSide === false && (rowCounter % 2 !== 0) ){
                    cards += '</div>';
                    changeSide = true;
                }
                rowCounter += 1;
            }
        };
        target.innerHTML = cards;
    };

    showTodaysFullDate(){
        let target = document.getElementById("shift-today-month-and-year");
        target.innerHTML = todaysDate.getDate() + "-" +  (todaysDate.getMonth()+1) + "-" + todaysDate.getFullYear();
    };

    goBack(){
        let target = document.getElementById("shift-today-month-and-year");
        let informationFromDocument = target.textContent;
        let textArray = informationFromDocument.split('-');

        let date = new Date(textArray[2], (textArray[1] - 1), textArray[0]);
        date.setDate(date.getDate() - 1);

        target.innerHTML = date.getDate() + '-' + (date.getMonth() + 1 ) + '-' + date.getFullYear();
        this.todaysShiftsCardGenerator();
    };

    goForward() {
        let target = document.getElementById("shift-today-month-and-year");
        let informationFromDocument = target.textContent;
        let textArray = informationFromDocument.split('-');

        let date = new Date(textArray[2], (textArray[1] - 1), textArray[0]);
        date.setDate(date.getDate() + 1);

        target.innerHTML = date.getDate() + '-' + (date.getMonth() + 1 ) + '-' + date.getFullYear();
        this.todaysShiftsCardGenerator();
    };

    somOm() {
        window.alert("Hej med dig. Jeg hedder Kaj.\n" +
            "Kaj, Kaj, Kaj, ja, det er mig.\n" +
            "Du-bi, Du-bi, Du-bi-dej.\n" +
            "Kaj, Kaj, Kaj, Kaj det er mig.\n" +
            "\n" +
            "Jeg kaldes skønne Kaj, grønne Kaj,\n" +
            "Kender du mig? Siger du nej\n" +
            "Skønne Kaj, grønne Kaj.\n" +
            "Det mig, mig, mig.\n" +
            "\n" +
            "Hej med dig. Jeg hedder Kaj.\n" +
            "Kaj, Kaj, Kaj, ja, det er mig.\n" +
            "Du-bi, Du-bi, Du-bi-dej.\n" +
            "Kaj, Kaj, Kaj, Kaj det er mig.");
    }
};

const ashiftOverwiev2 = new AshiftOverwiev2();
