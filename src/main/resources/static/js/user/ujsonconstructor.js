class Ujsonconstructor{
    constructor() {
    }
    savewish(date, wish, username){
        let data =
            {
                "date" : date,
                "wish": wish,
                "profile":
                    {
                    "username":username
                    }
            }
        uapicaller.post(JSON.stringify(data),"/wishes/save");
    }
}
const ujsonconstructor = new Ujsonconstructor();