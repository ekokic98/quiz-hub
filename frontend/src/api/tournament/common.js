import {basicGet, basicPost} from "api/common";

const tournamentUrl = "/tournament/api";

export const get = async (url) => {
    return await basicGet(tournamentUrl + url);
};

export const post = async (url, data) => {
    return await basicPost(tournamentUrl + url, data);
};
