import React from 'react';
import { Route, Switch } from 'react-router-dom';
import GuestRoute from 'routing/GuestRoute';

import LandingPage from 'pages/LandingPage';
import Categories from 'pages/Categories';
import Login from 'pages/Login';
import Register from 'pages/Register';
import PageNotFound from 'pages/PageNotFound';
import PrivateRoute from "routing/PrivateRoute";
import MyAccount from "pages/MyAccount";
import QuizContainer  from 'pages/QuizContainer/QuizContainer';
import App from "../pages/QuizExp/App";
import TApp from "../pages/Tournament/components/App";

const MyRoutes = () => {
    return (
        <Switch>
            <Route exact path="/" component={LandingPage}/>
            <Route path="/categories/*" component={Categories}/>
            <Route path="/categories" component={Categories}/>
            <Route path="/quiz/:id" component={App}/>
            <Route path="/play-quiz" component={TApp}/>
            <GuestRoute path="/login" component={Login}/>
            <GuestRoute path="/register" component={Register}/>
            <PrivateRoute path="/my-account" component={MyAccount}/>
            <Route component={PageNotFound}/>
        </Switch>
    );
}

export default MyRoutes;
