import axios from 'axios';
import {getToken} from 'utilities/localStorage'

export const hostUrl = process.env.REACT_APP_HOST_URL;

export const basicGet = async (url, data) => {
    return (await axios.get(hostUrl + url, data)).data;
};

export const basicPost = async (url, data) => {
    return (await axios.post(hostUrl + url, data)).data;
};

export const basicPut = async (url, data) => {
    return (await axios.put(hostUrl + url, data)).data;
};

export const authorizedPut = async (url, data) => {
    const config = {
        headers: { Authorization: "Bearer " + getToken() }
    };
    return (await axios.put(hostUrl + url, data, config )).data;
};

export const authorizedPost = async (url, data) => {
    const config = {
        headers: { Authorization: "Bearer " + getToken() }
    };
    return (await axios.post(hostUrl + url, data, config )).data;
};


export const authorizedGet = async (url, data) => {
    const config = {
        headers: { Authorization: "Bearer " + getToken() }
    };
    return (await axios.get(hostUrl + url, config )).data;
};