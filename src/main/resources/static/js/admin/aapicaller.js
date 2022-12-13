let api = "http://localhost:8080/admin/api";

class Aapicaller {

    constructor() {

    }

    async get(url) {
        let response = await fetch(api + url);
        return await response.json();  
    }

    async deleteNoReload(json, url) {
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
                ashiftOverwiev.cardGenerator()
            });
        } catch (error) {
            console.log(error);
        }
    }

    async postNoReload(json, url) {
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
                ashiftOverwiev.cardGenerator()
            });
        } catch (error) {
            console.log(error);
        }
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

    async put(json, url) {
        try {
            event.preventDefault();
            await fetch(api + url, {
                method: 'PUT',
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