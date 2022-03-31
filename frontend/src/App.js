import React from 'react';
import {Route, Routes} from "react-router-dom";
import './App.css';
import UserComponent from './components/UserComponent';
import ForgotPassword from "./components/ForgotPassword";
import HomePage from "./components/HomePage"
import Login from "./components/Login";
import NPSComponent from './components/NPSComponent';

function App() {
    return (
    <div className="App">
        <Routes>
            <Route exact path='/' element={<Login/>}/>
            <Route path='/signup' element={<UserComponent/>}/>
            <Route path='/forgot-password' element={<ForgotPassword/>}/>
            <Route path='/home' element={<HomePage/>}/>
            <Route path='/nps' element={<NPSComponent/>}/>
        </Routes>
    </div>

  );
}
export default App;
