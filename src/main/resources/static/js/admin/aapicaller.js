let api = "http://localhost:8080/admin/api";

class Aapicaller {

    constructor() {

    }

    async get(url) {
        let response = await fetch(api + url);
        return await response.json();  
    }

    async post(json, url) {
        try {
            event.preventDefault();
            await fetch(api + url, {
                method: 'POST',
                headers:
                    {
                        'Content-Type': 'application/json'
                    },
                body: json
            }).then(function() {
                location.reload();
            });
        } catch (error) {
            console.log(error);
        }     
    }

    async delete(json, url) {
        try {
            event.preventDefault();
            await fetch(api + url, {
                method: 'DELETE',
                headers:
                    {
                        'Content-Type': 'application/json'
                    },
                body: json
            }).then(function() {
                location.reload();
            });
        } catch (error) {
            console.log(error);
        }
    }

}

const aapicaller = new Aapicaller();