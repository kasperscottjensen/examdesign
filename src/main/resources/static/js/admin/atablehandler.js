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
                    <td>
                        <button class="btn btn-tertiary text-left ps-0"><a onclick="atablehandler.deleteuser(this.closest('tr'))">Disable</a></button>
                    </td>
                </tr>`;
            $('#allusers-table').append(row);
        };
    }

    deleteuser(row) {
        let username = row.children[0].innerHTML;
        ajsonconstructor.deleteuser(username);
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

}

const atablehandler = new Atablehandler();

