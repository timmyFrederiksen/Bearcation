import React from 'react';
import {Route, Routes} from "react-router-dom";
import ForgotPassword from "./components/ForgotPassword";
import HomePage from "./components/HomePage"
import NPSComponent from './oldComponents/NPSComponent';
import Home from "./components/LoadSearch";
import NewLogin from "./components/NewLogin";
import Explore from "./components/Explore";
import NewSignUp from './components/NewSignUp';

import './App.css';

function App() {
    return (
    <div className="App">
        <Routes>
            <Route exact path='/' element={<NewLogin/>}/>
            <Route path='/signup' element={<NewSignUp/>}/>
            <Route path='/forgot-password' element={<ForgotPassword/>}/>
            <Route path='/home' element={<HomePage/>}/>
            <Route path='/nps' element={<NPSComponent/>}/>
            <Route path='/search' element={<Home/>}/>
            <Route path='/explore' element={<Explore/>}/>
        </Routes>
    </div>

  );
}
export default App;
