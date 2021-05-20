import { get } from "./common";

const scoresUrl = "/scores";

export const getAllScoresToday = async () => {
    return await get(scoresUrl + "/all/today");
}
