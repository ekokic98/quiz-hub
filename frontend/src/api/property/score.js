import { get, post } from "./common";

const scoresUrl = "/scores";

export const getAllScoresToday = async () => {
    return await get(scoresUrl + "/all/today");
}

export const getAllScoresByQuiz = async (id) => {
    return await get(scoresUrl + "/all/quiz?id=" + id);
}

export const postScore = async (data) => {
 return await post(scoresUrl, data);
}
