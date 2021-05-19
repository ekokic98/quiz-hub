import React from 'react'
import Register from "pages/Register";
import { getUser } from "utilities/localStorage";

const EditTab = () => {
    const {firstName, lastName, username, email} = getUser();
    return (
        <>
            <Register initialValues={ {firstName, lastName, email, username} } registerMode={ false }/>
        </>
    )
}

export default EditTab;