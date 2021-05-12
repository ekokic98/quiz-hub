import {basicGet, basicPost} from "api/common";

const personUrl = "/person/api";

export const get = async (url, data) => {
    return await basicGet(personUrl + url, data);
};

export const post = async (url, data) => {
    return await basicPost(personUrl + url, data);
};
