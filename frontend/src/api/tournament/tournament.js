import { get } from "./common";

const tournamentsUrl = "/tournaments";

export const getAllTournaments = async () => {
    return await get(tournamentsUrl + "/all");
}

export const getLeaderboardForTournament = async (id) => {
    return await get(tournamentsUrl + "/leaderboard?id=" + id);
}

export const getTournament = async (id) => {
    return await get(tournamentsUrl + "?id=" + id);
}
