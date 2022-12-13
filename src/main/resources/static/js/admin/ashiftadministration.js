const todaysDate = new Date();

class AshiftOverwiev {

    constructor() {
    }

    deleteShift(shiftId){
        ajsonconstructor.deleteShift(shiftId, "/shift/delete");
    }

    async cardGenerator() {

        let target = document.getElementById("date-cards-html-target");

        //removes the old cards
        target.replaceChildren();

        let textArray = this.getPagesMonthAndYear();
        let currentMonthWritten = textArray[0];
        let currentYear = textArray[1];

        var cards;
        var allDatesOfCurrentMonth = this.getAllDaysInMonth(currentYear, this.getMonthFromText(currentMonthWritten))

        let monthStartDate = allDatesOfCurrentMonth[0].getFullYear() +"-"+ (allDatesOfCurrentMonth[0].getMonth()+1) +"-"+ allDatesOfCurrentMonth[0].getDate();
        let monthEndDate = allDatesOfCurrentMonth[allDatesOfCurrentMonth.length - 1].getFullYear() +"-"+ (allDatesOfCurrentMonth[allDatesOfCurrentMonth.length - 1].getMonth()+1) +"-"+ allDatesOfCurrentMonth[allDatesOfCurrentMonth.length - 1].getDate();

        let shifts = await aapicaller.get("/shift/findbydateinterval/" + monthStartDate + "/" + monthEndDate);

        for(let dateindex in allDatesOfCurrentMonth){
            var dateEntry = allDatesOfCurrentMonth[dateindex];
            let formattedDate = dateEntry.getDate() + "-" + (dateEntry.getMonth()+1) + "-" + dateEntry.getFullYear();
            cards =    `<!-- Row START -->
                            <div class="row border-bottom border-dark">
                                <!-- Date Card-->
                                <div class="col-2 d-flex my-2">
                                    <div class="card border border-dark bg-light-grey-custom text-center" style="min-width:135px; max-height:100px;">
                                        <div class="card-body">
                                            <h4 class="fw-b">Dato:</h4>
                                            <p class="mb-0 fs-5">${formattedDate}</p>
                                        </div>
                                    </div>
                                    <div style="height:30px; width:24px;" data-bs-toggle="modal" data-bs-target="#addshift-modal" onclick=" atablehandler.allUsersForShiftModal(); ashiftOverwiev.getDayFromList(${dateEntry.getDate()}, ${(dateEntry.getMonth()+1)}, ${dateEntry.getFullYear()});"> <i class="fa-solid fa-user-plus fa-xl mx-3 mt-5 btn"></i> </div>
                                </div> `;

            //Counter to tell if the cards need to skip a space in the html
            let addedShiftCounter = 0;

            for(let shiftIndex in shifts){

                var shiftEntry = shifts[shiftIndex];

                //Formatting date so they can be tested against each other
                let dateEntryMonth = (dateEntry.getMonth()+1).toString();
                let dateEntryDate = dateEntry.getDate().toString();
                if(dateEntryDate.length < 2){
                    dateEntryDate = "0" + dateEntryDate;
                }
                if(dateEntryMonth.length < 2){
                    dateEntryMonth = "0" + dateEntryMonth;
                }

                //if test is true, the shift belongs to this date and will be placed as a card to the right of it
                if(shiftEntry.shift.date === dateEntry.getFullYear() + "-" + dateEntryMonth + "-" + dateEntryDate){
                    //Counts 1 up for every shift on that date
                    +addedShiftCounter++;

                    cards += `<!-- Worker/Shift Card START-->
                            <div id="" class="col-2 text-center my-2">
                                <div class="card border border-dark bg-light-grey-custom">
                                    <div class="card-body d-flex">
                                        <div class="col-10">
                                            <h4 class="fw-b">${shiftEntry.username}</h4>
                                            <p class="mb-0 fs-5">${shiftEntry.shift.timeStart} - ${shiftEntry.shift.timeEnd}</p>
                                        </div>
                                         <div class="col-2">
                                            <i class="fa-solid fa-trash fa-xl btn" onclick="ashiftOverwiev.deleteShift(${shiftEntry.shift.id})"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- Worker/Shift Card END-->`;
                    /**
                     * if there have been added 5, 10 or 15 shifts,
                     * one card will be left empty to create space between the date card and shift cards
                     */
                    if(addedShiftCounter === 5 || addedShiftCounter === 10 || addedShiftCounter === 15){
                        cards += `<!-- Worker/Shift Card START-->
                            <div class="col-2 my-2">
                            </div>
                            <!-- Worker/Shift Card END-->`;
                    };
                };
            };

            cards += ` </div>
                    <!-- Row END-->`;

            target.innerHTML += cards;
        };
    };

    //returns an array with the current month and year
    getPagesMonthAndYear(){
        let target = document.getElementById("shift-administration-month-and-year");
        let informationFromDocument = target.textContent;
        let textArray = informationFromDocument.split(' ');
        return textArray;
    };

    goBack(){
        let target = document.getElementById("shift-administration-month-and-year");
        let textArray = this.getPagesMonthAndYear();
        let currentMonthWritten = textArray[0];
        let currentYear = textArray[1];

        let currentMonth = this.getMonthFromText(currentMonthWritten);
        //The new month and year to be displayed
        let nextMonth;
        let nextYear = currentYear;
        //Checking if the next month is December (Going backwards)
        if(currentMonth === 0){
            nextMonth = 11;
            nextYear = +currentYear - 1;
        }else {
            nextMonth = +currentMonth - 1;
        };
        target.innerHTML = this.getMonthWrittenFromNumberOfMonth(nextMonth) + " " + nextYear;
        this.cardGenerator();
    };

    goForward() {
        let target = document.getElementById("shift-administration-month-and-year");
        let textArray = this.getPagesMonthAndYear();
        let currentMonthWritten = textArray[0];
        let currentYear = textArray[1];

        let currentMonth = this.getMonthFromText(currentMonthWritten);
        //The new month and year to be displayed
        let nextMonth;
        let nextYear = currentYear;
        //Checking if the next month is January (Going forward)
        if(currentMonth === 11){
            nextMonth = 0;
            nextYear = +currentYear + 1;
        }else {
            nextMonth = +currentMonth + 1;
        };
        target.innerHTML = this.getMonthWrittenFromNumberOfMonth(nextMonth) + " " + nextYear;
        this.cardGenerator();
    };

    showTodaysMonthAndYear(){
        let target = document.getElementById("shift-administration-month-and-year");
            target.innerHTML = this.getMonthWrittenFromNumberOfMonth(todaysDate.getMonth()) + " " +  todaysDate.getFullYear();
    };

    getAllDaysInMonth(year, month) {

        //makes a date from the first day of the month. 1 = first day
        const date = new Date(year, month, 1);

        const dates = [];
        //Finds alle the days in the current month
        while (date.getMonth() === month) {
            dates.push(new Date(date));
            date.setDate(date.getDate() + 1);
        };
        return dates;
    };

    /**
     * Returns the written month from the number of the month
     */
    getMonthWrittenFromNumberOfMonth(numberOfMonth){

        let writtenMonth;

        switch (numberOfMonth) {
            case 0:
                writtenMonth = "Januar";
                break;
            case 1:
                writtenMonth = "Februar";
                break;
            case 2:
                writtenMonth = "Marts";
                break;
            case 3:
                writtenMonth = "April";
                break;
            case 4:
                writtenMonth = "Maj";
                break;
            case 5:
                writtenMonth = "Juni";
                break;
            case 6:
                writtenMonth = "Juli";
                break;
            case 7:
                writtenMonth = "August";
                break;
            case 8:
                writtenMonth = "September";
                break;
            case 9:
                writtenMonth = "Oktober";
                break;
            case 10:
                writtenMonth = "November";
                break;
            case 11:
                writtenMonth = "December";
                break;
            default:
                writtenMonth = "null";
        };

        return writtenMonth;
    };

    /**
     * Returns the number of the month from the moths written name
     */
    getMonthFromText(writtenMonth){

        let month;

        switch (writtenMonth) {
            case "Januar":
                month = 0;
                break;
            case "Februar":
                month = 1;
                break;
            case "Marts":
                month = 2;
                break;
            case "April":
                month = 3;
                break;
            case "Maj":
                month = 4;
                break;
            case "Juni":
                month = 5;
                break;
            case "Juli":
                month = 6;
                break;
            case "August":
                month = 7;
                break;
            case "September":
                month = 8;
                break;
            case "Oktober":
                month = 9;
                break;
            case "November":
                month = 10;
                break;
            case "December":
                month = 11;
                break;
            default:
                month = "null";
        };

        return month;
    };


    getDayFromList(date, month, year) {
        //Takes the date as 3 variables, so that JS doesn't see it as a calculation...
        document.getElementById("modal-createshift").innerHTML = "Dato for vagt: " + date + "-" + month + "-" + year;

        let dateString = date.toString();
        let monthString = month.toString();
        if(dateString.length < 2){
            dateString = "0" + dateString;
        }
        if(monthString.length < 2){
            monthString = "0" + monthString;
        }
        document.getElementById("addshiftdate").value = year + "-" + monthString + "-" + dateString;
    };
};

const ashiftOverwiev = new AshiftOverwiev();
