import {basicGet, basicPost} from "api/common";

const personUrl = "/person/api";

export const get = async (url, data) => {
    return basicGet(personUrl + url, data);
};

export const post = async (url, data) => {
    return basicPost(personUrl + url, data);
};
