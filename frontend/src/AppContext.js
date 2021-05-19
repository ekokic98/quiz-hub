import React, { createContext, useContext, useState } from "react";
import { validToken } from "utilities/common";
import axios from "axios";

export const UserContext = createContext({});

export const useUserContext = () => useContext(UserContext);

export const AppProvider = ({ children }) => {
    const [loggedIn, setLoggedIn] = useState(validToken());

    const handleError = (error) => {
        const errorMessage = error.response.data.message;
        console.error(errorMessage)
        return Promise.reject(error);
    }

    axios.interceptors.response.use((response) => response, handleError);

    return (
        <UserContext.Provider value={{ loggedIn, setLoggedIn }}>
            {children}
        </UserContext.Provider>
    );
};
