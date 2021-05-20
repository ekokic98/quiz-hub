import { get } from "./common";

const tournamentsUrl = "/tournaments";

export const getAllTournaments = async () => {
    return await get(tournamentsUrl + "/all");
}
