import { get, post } from "./common";
import { getUser } from "../../utilities/localStorage";

const scoresUrl = "/scores";

export const getAllScoresToday = async () => {
    return await get(scoresUrl + "/all/today");
}

export const getAllScoresByQuiz = async (id) => {
    return await get(scoresUrl + "/all/quiz?id=" + id);
}

export const getAllScoresByUser = async () => {
    const username = getUser().username;
    return await get(scoresUrl + "/all/user?username=" + username);
}

export const postScore = async (data) => {
    return await post(scoresUrl, data);
}
