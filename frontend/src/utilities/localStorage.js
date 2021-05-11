const tokenItem = 'quizhub-token';

// return token from local storage
export const getToken = () => {
    return localStorage.getItem(tokenItem) || null;
}
