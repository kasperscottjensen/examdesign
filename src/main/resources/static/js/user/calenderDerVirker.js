today = new Date();
currentMonth = today.getMonth();
currentYear = today.getFullYear();
selectYear = document.getElementById("year");
selectMonth = document.getElementById("month");

months = ["Januar", "Februar", "Marts", "April", "Maj", "Juni", "Juli", "August", "September", "Oktober", "Novemebr", "December"];

monthAndYear = document.getElementById("monthAndYear");
showCalendar(currentMonth, currentYear);
showWishOnCalender(currentMonth, currentYear);


function next() {
    currentYear = (currentMonth === 11) ? currentYear + 1 : currentYear;
    currentMonth = (currentMonth + 1) % 12;
    showCalendar(currentMonth, currentYear);
    showWishOnCalender(currentMonth, currentYear);
}

function previous() {
    currentYear = (currentMonth === 0) ? currentYear - 1 : currentYear;
    currentMonth = (currentMonth === 0) ? 11 : currentMonth - 1;
    showCalendar(currentMonth, currentYear);
    constructWishOnDate(currentMonth, currentYear);
}

function jump() {
    currentYear = parseInt(selectYear.value);
    currentMonth = parseInt(selectMonth.value);
    showCalendar(currentMonth, currentYear);
    constructWishOnDate(currentMonth, currentYear);
}

function showCalendar(month, year) {

    let firstDay = (new Date(year, month)).getDay();

    tbl = document.getElementById("calendar-body"); // body of the calendar

    // clearing all previous cells
    tbl.innerHTML = "";

    // filing data about month and in the page via DOM.
    monthAndYear.innerHTML = months[month] + " " + year;
    selectYear.value = year;
    selectMonth.value = month;

    // creating all cells
    let date = 1;
    for (let i = 0; i < 6; i++) {
        // creates a table row
        let row = document.createElement("tr");

        //creating individual cells, filing them up with data.
        for (let j = 0; j < 7; j++) {
            if (i === 0 && j < firstDay) {
                cell = document.createElement("td");
                cellText = document.createTextNode("");
                cell.appendChild(cellText);
                row.appendChild(cell);
            }
            else if (date > daysInMonth(month, year)) {
                break;
            }

            else {
                cell = document.createElement("td");
                cell.ondblclick = function() { $('#wishingModal').modal('show');
                };
                cellP = document.createElement("p");
                cellText = document.createTextNode(date);
                cellP.appendChild(cellText);
                cellP.classList.add("noclick");
                cell.id = "cell-id-" + date;
                if (date === today.getDate() && year === today.getFullYear() && month === today.getMonth()) {
                    cell.classList.add("bg-info");
                } // color today's date
                cell.appendChild(cellP);
                row.appendChild(cell);
                date++;
            }


        }

        tbl.appendChild(row); // appending each row into calendar body.
    }
    updateModal(month,year);
}

// check how many days in a month code from https://dzone.com/articles/determining-number-days-month
function daysInMonth(iMonth, iYear) {
    return 32 - new Date(iYear, iMonth, 32).getDate();
}

function updateModal(month, year){
    let getMonth = month + 1;
    document.getElementById("showUsername").innerHTML = localStorage.getItem("username");
    var rows = document.getElementsByTagName('tr');
    Array.from(rows).forEach(function(tr) {
        tr.addEventListener('dblclick', function(event) {
            if(event.target.textContent < 10){
                console.log(event.target.textContent)
                document.getElementById("current-date").innerHTML = 0 + event.target.textContent + "/" + getMonth + "/" + year;
                constructWishOnDate();
            }
                else{
                document.getElementById("current-date").innerHTML = event.target.textContent + "/" + getMonth + "/" + year;
                constructWishOnDate();
            }
        });
    });
}
function constructWishOnDate(){
    let getWishes = JSON.parse(localStorage.getItem("wishesArray"));
    let currentDate = document.getElementById("current-date").innerHTML.valueOf();
    console.log(currentDate);
    const [day, month, year] = currentDate.split('/');
    const result = [year, month, day].join('-');
    console.log(result);
    for (let index in getWishes) {
        if (getWishes[index].date === result) {
            console.log(getWishes[index].wish);
            document.getElementById("userWish").value = getWishes[index].wish;
        }
    }
}
async function getWishForUser(){
    let uapicaller = new Uapicaller();
    let username = localStorage.getItem("username");
    let userWishes = await uapicaller.get("/wishes/findAllByUser/" + username);
    console.log(userWishes);
    localStorage.setItem("wishesArray", JSON.stringify(userWishes));
}
function showWishOnCalender(month, year){
    let getMonth = month + 1;
    let userWishes = JSON.parse(localStorage.getItem("wishesArray"));
    let date;
    for(let i = 1; i < 32; i++){
        if(i < 10){
            date = [year, getMonth, "0"+i].join('-');
        }else{
            date = [year, getMonth, i].join('-');
        }
        for (let index in userWishes) {
            if(userWishes[index].date === date){
                if(i < 10){
                    cell = document.getElementById("cell-id-" + i);
                    cellTag = document.createElement("i");
                    if(userWishes[index].wish === "Kan godt"){
                        cellTag.classList.add("fa-solid", "fa-square-check", "fa-xl", "ms-5", "noclick");
                    }else if(userWishes[index].wish === "Kan ikke"){
                        cellTag.classList.add("fa-solid", "fa-square-minus", "fa-xl", "ms-5", "noclick");
                    }
                    cellTag.style.paddingLeft = "39.56px";
                    cell.appendChild(cellTag);
                }else{
                    cell = document.getElementById("cell-id-" + i);
                    cellTag = document.createElement("i");
                    if(userWishes[index].wish === "Kan godt"){
                        cellTag.classList.add("fa-solid", "fa-square-check", "fa-xl", "ms-5", "noclick");
                    }else if(userWishes[index].wish === "Kan ikke"){
                        cellTag.classList.add("fa-solid", "fa-square-minus", "fa-xl", "ms-5", "noclick");
                    }
                    cellTag.style.paddingLeft = "30px";
                    cell.appendChild(cellTag);
                }
            }
        }
    }
}
function submitWish(){
    event.preventDefault();
    console.log("hej kaj");
    let currentDate = document.getElementById("current-date").innerHTML.valueOf();
    const [day, month, year] = currentDate.split('/');
    const result = [year, month, day].join('-');
    console.log(result);
    let getUsername = localStorage.getItem("username");
    console.log(getUsername);
    let wish = $('#userWish').val();
    console.log(wish)
    ujsonconstructor.savewish(result,wish,getUsername);
}