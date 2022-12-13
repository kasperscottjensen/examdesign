class Atablehandler {

    constructor() {

    }

    async allusers() {
        let users = await aapicaller.get("/user/findall");
        for (let index in users) {
            let entry = users[index];
            let row =
                `<tr>
                    <td>${entry.username}</td>
                    <td>${entry.enabled}</td>
                    <td>${entry.password}</td>
                    <td>${entry.authority.authority}</td>
                    <td>
                    <div  data-bs-toggle="modal" data-bs-target="#edituser-modal"> <button class="btn btn-tertiary" onclick="atablehandler.setEditUserInformation(this.closest('tr'))">Opdater</button> </div>
                    </td>
                </tr>`;
            $('#allusers-table').append(row);
        };
    }

   async setEditUserInformation(row) {

        let username = row.children[0].innerHTML;
        let enabled = row.children[1].innerHTML;
        let authority = row.children[3].innerHTML;

        document.getElementById("edituser-username").value = username;

        document.getElementById("enabled-true").removeAttribute("selected");
        document.getElementById("enabled-false").removeAttribute("selected");
        document.getElementById("authority-admin").removeAttribute("selected");
        document.getElementById("authority-user").removeAttribute("selected");

        if (enabled === "true") {
            document.getElementById("enabled-true").setAttribute('selected','selected')
        } else {
            document.getElementById("enabled-false").setAttribute('selected','selected')
        }

        if (authority === "ROLE_ADMIN") {
            document.getElementById("authority-admin").setAttribute('selected','selected')
        } else {
            document.getElementById("authority-user").setAttribute('selected','selected')
        }

    }

    async allUsersForShiftModal(){
        let users = await aapicaller.get("/user/findall");
        let target = document.getElementById("addshiftusername");
        let options = "" //"<option disabled>Vælg medarbejder</option>";
        for (let index in users) {
            let entry = users[index];
            options += `<option value="${entry.username}">${entry.username}</option>`;
        }
        target.innerHTML = options;
    }


}

const atablehandler = new Atablehandler();

