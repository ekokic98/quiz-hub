import { authorizedPut, basicGet, basicPost, basicPut } from "api/common";

const personUrl = "/person/api";

export const get = async (url, data) => {
    return await basicGet(personUrl + url, data);
};

export const post = async (url, data) => {
    return await basicPost(personUrl + url, data);
};

export const put = async (url, data) => {
    return await authorizedPut(personUrl + url, data);
};
