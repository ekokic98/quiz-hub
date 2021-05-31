import { get } from "./common";

const qaUrl = "/answers";

// returns all questions and answers for provided quiz (via id)
export const getQuizData = async (quizId) => {
    return await get(qaUrl + "/quiz?id=" + quizId);
}

// method that grabs quiz for updating quiz
export const getFullQuiz = async (quizId) => {
    return await get(qaUrl + "/quizdata?id=" + quizId);
}
