import {get, post} from "./common";

const categoriesUrl = "/categories";

export const getAllCategories = () => {
    return get(categoriesUrl + "/all");
}

export const getCategory = (id) => {
    return get(categoriesUrl, id);
}

export const addCategory = (category) => {
    return post(categoriesUrl, category);
}

