import React from 'react';
import {BrowserRouter, Routes, Route} from "react-router-dom";
import ForgotPassword from "../components/ForgotPassword";
import App from "../App";
import UserComponent from "../components/UserComponent";
import HomePage from "../components/HomePage"
import NPSComponent from '../components/NPSComponent';

export default function RouterConfig() {

    /*
    Here we define the route path and its corresponding components
    */
    return (
        <BrowserRouter>
            <Routes>
                <Route exact path='/' element={<App/>}/>
                <Route path='/UserComponent' element={<UserComponent/>}/>
                <Route path='/forgot-password' element={<ForgotPassword/>}/>
                <Route path='/home' element={<HomePage/>}/>
                <Route path='/nps' element={<NPSComponent/>}/>
            </Routes>
        </BrowserRouter>
    );
}
