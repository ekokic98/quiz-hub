import { get } from "./common";

const qaUrl = "/answers";

// returns all questions and answers for provided quiz (via id)
export const getQuizData = async (quizId) => {
    return await get(qaUrl + "/quiz?id=" + quizId);
}
