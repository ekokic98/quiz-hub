import axios from 'axios';

export const hostUrl = process.env.REACT_APP_HOST_URL;

export const basicGet = async (url, data) => {
    return (await axios.get(hostUrl + url, data)).data;
};

export const basicPost = async (url, data) => {
    return (await axios.post(hostUrl + url, data)).data;
};
