import React from 'react';
import { Route, Switch } from 'react-router-dom';
import GuestRoute from 'routing/GuestRoute';

import LandingPage from 'pages/LandingPage';
import Categories from 'pages/Categories';
import Tournaments from 'pages/Tournaments';
import Login from 'pages/Login';
import Register from 'pages/Register';
import PageNotFound from 'pages/PageNotFound';
import PrivateRoute from "routing/PrivateRoute";
import MyAccount from "pages/MyAccount";
import App from "pages/PlayQuiz/App";
import QuizMain from "pages/PlayQuiz/QuizMain";
import  CreateUpdateQuiz from 'pages/CreateUpdateQuiz';
import Admin from "../pages/Admin";

const MyRoutes = () => {
    return (
        <Switch>
            <Route exact path="/" component={LandingPage}/>
            <Route path="/categories/*" component={Categories}/>
            <Route path="/categories" component={Categories}/>
            <Route path="/quiz/play/:id" component={App}/>
            <Route path="/quiz/:id" component={QuizMain}/>
            <Route path="/tournament/:id" component={Tournaments}/>
            <PrivateRoute path="/update-quiz/:id" component={CreateUpdateQuiz}/>
            <PrivateRoute path="/create-quiz" component={CreateUpdateQuiz}/>
            <GuestRoute path="/login" component={Login}/>
            <GuestRoute path="/register" component={Register}/>
            <PrivateRoute path="/my-account" component={MyAccount}/>
            <PrivateRoute path="/admin" component={Admin}/>
            <Route component={PageNotFound}/>
        </Switch>
    );
}

export default MyRoutes;
