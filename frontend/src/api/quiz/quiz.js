import { get, post } from "./common";

const quizzesUrl = "/quizzes";

export const getAllQuizzes = async (tournamentId) => {
    return await get(quizzesUrl + "/all?tournament=" + tournamentId);
}

export const getQuizzesByCategory = async (categoryId) => {
    return await get(quizzesUrl + "/category?id=" + categoryId);
}

export const getQuizzesForTournament = async (id) => {
    return await get(quizzesUrl + "/tournament?id=" + id);
}

export const updateQuizReq = async (updatedQuiz) => {
    return await post(quizzesUrl + "/update-quiz", updatedQuiz);
}

export const createQuizReq = async (updatedQuiz) => {
    return await post(quizzesUrl + "/create-quiz", updatedQuiz);
}
