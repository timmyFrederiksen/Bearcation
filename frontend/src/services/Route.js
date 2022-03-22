import React from 'react';
import {BrowserRouter, Routes, Route} from "react-router-dom";
import ForgotPassword from "../components/ForgotPassword";
import App from "../App";
import UserComponent from "../components/UserComponent";

export default function RouterConfig() {

    /*
    Here we define the route path and its corresponding components
    */
    return (
        <BrowserRouter>
            <Routes>
                <Route exact path='/' element={<App/>}/>
                <Route path='/UserComponent' element={<UserComponent/>}/>
                <Route path='/ForgotPassword' element={<ForgotPassword/>}/>
            </Routes>
        </BrowserRouter>
    );
}
