import React, { useState } from "react";
import Box from '@mui/material/Box';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';
import { useNavigate, Link } from "react-router-dom"

import axios from 'axios';
import '../styles/login.css'
import "bootstrap/dist/css/bootstrap.min.css"

const handleSubmit = async (e, navigate, username, password) => {
    e.preventDefault();
    const userDto = {
        username: username,
        password: password
    };
    let response;
    await axios.post("http://localhost:80/user/check", userDto)
        .then(res => {
            console.log(res);
            response = res.data;
            //response = res.data.username;
        })

    console.log("response " + response)
    if (response !== "") {
        navigate('/home')
    } else {
        alert("Credentials do not match any account.")
    }
}


function NewLogin() {


    const [username, setUsername] = useState();
    const [password, setPassword] = useState();
    const [role, setRole] = useState('');

    const handleChange = (event) => {
        setRole(event.target.value);
    };

    const navigate = useNavigate();


    return (
        <div className="login-page">
            <div className="login-body">
                <h2 className="welcome-tag">Welcome to Bearcation</h2>
                <form className="login-form" onSubmit={e => handleSubmit(e, navigate, username, password)} >
                    <div className="username-group form-group">
                        <input name="username" className="form-control" placeholder="Username" value={username} type="text" onChange={e => setUsername(e.target.value)} required />
                    </div>
                    <div className="password-group form-group">
                        <input name="password" className="form-control" placeholder="Password" value={password} type="password" onChange={e => setPassword(e.target.value)} required />
                    </div>
                    <div className="login-role-container">
                        <FormControl size="small" className="login-role-form">
                            <InputLabel id="demo-simple-select-label">Role</InputLabel>
                            <Select
                                labelId="demo-simple-select-label"
                                value={role}
                                label="Role"
                                onChange={handleChange}
                                required
                                PaperProps={{
                                    elevation: 0,
                                    sx: {
                                      overflow: 'visible',
                                      filter: 'drop-shadow(0px 2px 8px rgba(0,0,0,0.32))',
                                      mt: 1.5,
                                      '&.MuiMenuItem-root': {
                                        display: 'flex'
                                      },
                                    },
                                  }}
                            >
                                <MenuItem value="Customer">Customer</MenuItem>
                                <MenuItem value="Owner">Owner</MenuItem>
                            </Select>
                        </FormControl>
                    </div>
                    <input type="submit" className="btn btn-dark btn-block submit" value="Submit" />
                </form>
                <div className="forgot-password">
                    <nav>
                        <Link className="login-text" to="/forgot-password">Forgot Password?</Link>
                    </nav>
                </div>
                <div className="createAccount">
                    <nav>
                        <Link className="login-text" to="/signup">Create Account</Link>
                    </nav>
                </div>
                <div className="proceedAsGuest">
                    <nav>
                        <Link className="login-text" to="/search">Proceed as Guest</Link>
                    </nav>
                </div>
            </div>
        </div>
    )
}


export default NewLogin;