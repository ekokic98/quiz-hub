import React from 'react';
import { Route, Switch } from 'react-router-dom';
import GuestRoute from 'routing/GuestRoute';

import LandingPage from 'pages/LandingPage';
import Categories from 'pages/Categories';
import Login from 'pages/Login';
import Register from 'pages/Register';
import PageNotFound from 'pages/PageNotFound';

const MyRoutes = () => {
    return (
        <Switch>
            <Route exact path="/" component={LandingPage}/>
            <Route path="/categories" component={Categories}/>
            <GuestRoute path="/login" component={Login}/>
            <GuestRoute path="/register" component={Register}/>
            <Route component={PageNotFound}/>
        </Switch>
    );
}

export default MyRoutes;
