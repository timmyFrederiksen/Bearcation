import React, { useState} from "react";
import { useNavigate, Link } from "react-router-dom"

import axios from 'axios';
import '../styles/signup.css'
import "bootstrap/dist/css/bootstrap.min.css"

const signup = async (username, password) => {
    return true;
}


const handleSubmit = async (e, navigate, username, password) => {
    e.preventDefault();

    const response = await signup(username, password);
    if(response != null){
        navigate('/')
    }else{
        alert("Credentials do not match any account.")
    }
}


function NewSignUp(){
    const [username, setUsername] = useState();
    const [password, setPassword] = useState();
    const [confirmPassword, setConfirmPassword] = useState();

    const navigate = useNavigate();

    return (
        <div className="signup-page">
            <div className="signup-body">
                <h2 className="create-tag">Create your Bearcation Account</h2>
                <form className = "signup-form" onSubmit={e => handleSubmit(e, navigate, username, password)} >
                    <div className="username-group form-group">
                        <input name = "username" className="form-control" placeholder="Username" value={username} type="text" onChange={e => setUsername(e.target.value)} required />
                    </div>
                    <div className="password-group form-group">
                        <input name = "password" className="form-control" placeholder="Password" value={password} type="password" onChange={e => setPassword(e.target.value)} required />
                    </div>
                    <div className="confirm-password-group form-group">
                        <input name = "confirm-password" className="form-control" placeholder="Confirm Password" value={confirmPassword} type="password" onChange={e => setConfirmPassword(e.target.value)} required />
                    </div>
                    <input type="submit" className="btn btn-dark btn-block submit" value="Submit" />
                </form>
                <div className="login-tag">
                    <nav>
                        <Link className="signup-text" to="/">Sign In</Link>
                    </nav>
                </div>
            </div>
        </div>
    )
}


export default NewSignUp;