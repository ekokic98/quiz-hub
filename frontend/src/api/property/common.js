import {basicGet, basicPost} from "api/common";

const propertyUrl = "/property/api";

export const get = async (url, data) => {
    return basicGet(propertyUrl + url, data);
};

export const post = async (url, data) => {
    return basicPost(propertyUrl + url, data);
};
