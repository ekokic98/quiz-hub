import { get, post } from "./common";

const commentsUrl = "/comments";

export const getCommentsByQuiz = async (id) => {
    return await get(commentsUrl + "/quiz?quizId=" + id);
}

export const addComment = async (body) => {
    return await post(commentsUrl, body);
}
