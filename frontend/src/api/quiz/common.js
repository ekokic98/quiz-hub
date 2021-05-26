import {basicGet, basicPost} from "api/common";

const quizUrl = "/quiz/api";

export const get = async (url) => {
    return await basicGet(quizUrl + url);
};

export const post = async (url, data) => {
    return await basicPost(quizUrl + url, data);
};
