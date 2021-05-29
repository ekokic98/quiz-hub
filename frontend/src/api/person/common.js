import { basicGet, basicPut, basicPost } from "api/common";

const personUrl = "/person/api";

export const get = async (url) => {
    return await basicGet(personUrl + url);
};

export const post = async (url, data) => {
    return await basicPost(personUrl + url, data);
};

export const put = async (url, data) => {
    return await basicPut(personUrl + url, data);
};
