import React from 'react';
import './App.css';
import UserComponent from './components/UserComponent';
import ForgotPassword from "./components/ForgotPassword";
import {Route, Routes} from "react-router-dom";
import Login from "./components/Login";

function App() {
    return (
    <div className="App">
        <Routes>
            <Route exact path='/' element={<Login/>}/>
            <Route exact path='/CreateAccount' element={<UserComponent/>}/>
            <Route path='/ForgotPassword' element={<ForgotPassword/>}/>
        </Routes>
    </div>

  );
}
export default App;
