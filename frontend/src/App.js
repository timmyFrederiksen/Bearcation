import React from 'react';
import {Route, Routes} from "react-router-dom";
import './App.css';
import UserComponent from './components/UserComponent';
import ForgotPassword from "./components/ForgotPassword";
import HomePage from "./components/HomePage"
import Login from "./components/Login";
import NPSComponent from './oldComponents/NPSComponent';
import Home from "./components/GMap";
import NewLogin from "./components/NewLogin";


function App() {
    return (
    <div className="App">
        <Routes>
            <Route exact path='/' element={<NewLogin/>}/>
            <Route path='/signup' element={<UserComponent/>}/>
            <Route path='/forgot-password' element={<ForgotPassword/>}/>
            <Route path='/home' element={<HomePage/>}/>
            <Route path='/nps' element={<NPSComponent/>}/>
            <Route path='/search' element={<Home/>}/>
        </Routes>
    </div>

  );
}
export default App;
