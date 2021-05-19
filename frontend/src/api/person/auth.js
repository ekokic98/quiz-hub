import { post } from "./common";

const authUrl = "/auth";

export const signUp = async (body) => {
    return await post(authUrl + "/signup", body);
}

export const login = async (body) => {
    return await post(authUrl + "/login", body);
}
