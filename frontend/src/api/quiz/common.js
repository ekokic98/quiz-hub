import {basicGet, basicPost} from "api/common";

const quizUrl = "/quiz/api";

export const get = async (url, data) => {
    return basicGet(quizUrl + url, data);
};

export const post = async (url, data) => {
    return basicPost(quizUrl + url, data);
};
