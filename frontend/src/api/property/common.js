import { basicDelete, basicGet, basicPost } from "api/common";

const propertyUrl = "/property/api";

export const get = async (url) => {
    return await basicGet(propertyUrl + url);
};

export const post = async (url, data) => {
    return await basicPost(propertyUrl + url, data);
};

export const del = async (url) => {
    return await basicDelete(propertyUrl + url);
};
