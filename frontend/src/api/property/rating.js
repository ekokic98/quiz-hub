import { get, post } from "./common";

const ratingsUrl = "/ratings";

export const getAllRatingsByQuiz = async (id) => {
    return await get(ratingsUrl + "/all/quiz?id=" + id);
}

export const getRatingByUserAndQuiz = async (userId, quizId) => {
    return await get(ratingsUrl + "/quiz?quizId=" + quizId + "&userId=" + userId);
}

export const addRating = async (body) => {
    return await post(ratingsUrl, body);
}
