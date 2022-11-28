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
                        <button class="btn btn-tertiary text-left ps-0"><a onclick="atablehandler.deleteuser(this.closest('tr'))">| DELETE |</a></button>
                    </td>
                <tr>`;
            $('#allusers-table').append(row);
        };
    }

    deleteuser(row) {
        let username = row.children[0].innerHTML;
        ajsonconstructor.deleteuser(username);
    }

}

const atablehandler = new Atablehandler();