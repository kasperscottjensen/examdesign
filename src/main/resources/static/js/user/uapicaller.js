let api = "http://localhost:8080/user/api";

class Uapicaller {

    constructor() {

    }

    async get(url) {
        let response = await fetch(api + url);
        return await response.json();
    }

    async postWhishes(json, url) {
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
                getWishForUser();
                setTimeout(function () {
                    location.reload()
                }, 250)
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
    async deleteById(url) {
        try {
            event.preventDefault();
            await fetch(api + url, {
                method: 'DELETE',
                headers:
                    {
                        'Content-Type': 'application/json'
                    },
            }).then(function() {
                location.reload();
            });
        } catch (error) {
            console.log(error);
        }
    }

}

const uapicaller = new Uapicaller();