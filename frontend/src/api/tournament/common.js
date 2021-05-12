import {basicGet, basicPost} from "api/common";

const tournamentUrl = "/tournament/api";

export const get = async (url, data) => {
    return await basicGet(tournamentUrl + url, data);
};

export const post = async (url, data) => {
    return await basicPost(tournamentUrl + url, data);
};
