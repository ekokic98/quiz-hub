import {get, post} from "./common";

const categoriesUrl = "/categories";

export const getAllCategories = async () => {
    return await get(categoriesUrl + "/all");
}

export const getCategory = async (id) => {
    return await get(categoriesUrl + "?id=" + id);
}

export const addCategory = async (category) => {
    return await post(categoriesUrl, category);
}
