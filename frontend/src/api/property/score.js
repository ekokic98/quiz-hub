import { get, authPost} from "./common";


const scoresUrl = "/scores";

export const getAllScoresToday = async () => {
    return await get(scoresUrl + "/all/today");
}

export const postScore = async (data) => {
 return await authPost(scoresUrl, data);
}
