import {basicGet, basicPost} from "api/common";

const propertyUrl = "/property/api";

export const get = async (url, data) => {
    return await basicGet(propertyUrl + url, data);
};

export const post = async (url, data) => {
    return await basicPost(propertyUrl + url, data);
};
