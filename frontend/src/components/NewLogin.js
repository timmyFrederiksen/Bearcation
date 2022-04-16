import React, { useState} from "react";
import { useNavigate, Link } from "react-router-dom"
import axios from 'axios';



const login = async (props) => {
    const userDto = {
        password: props.password,
        username: props.username
    };

    axios.post("http://localhost:80/user/users", userDto)
        .then(res => {
            console.log(res);
            alert("Welcome " + this.state.username + " " + this.state.password);
        })

    return userDto;
}


 const handleSubmit = async (e, navigate, username, password) => {

    e.preventDefault();

    const response = await login(username, password);
    if(response != null){
        navigate('/home')
    }else{
        alert("Credentials do not match any account.")
    }
}


function NewLogin(){


    const [username, setUsername] = useState();
    const [password, setPassword] = useState();

    const navigate = useNavigate();


    return (
        <div className="loginBody">
            <h1>Login</h1>
            <form className = "loginForm" onSubmit={e => handleSubmit(e, navigate, username, password)} >
                <div className="usernameArea">
                    <label>
                        Username:
                    </label>
                    <input name = "username" className="usernameBox" value={username} type="text" onChange={e => setUsername(e.target.value)} required />
                </div>
                <div className="passwordArea">
                    <label>
                        Password:
                    </label>
                    <input name = "password" className="passwordBox" value={password} type="text" onChange={e => setPassword(e.target.value)} required />
                </div>
                <div className="submitArea">
                    <input type="submit" value="Submit" />
                </div>
            </form>
            <div className={"forgotPassword"}>
                <nav>
                    <Link to="/forgot-password">Forgot Password?</Link>
                </nav>
            </div>
            <div className={"createAccount"}>
                <nav>
                    <Link to="/signup">Create Account</Link>
                </nav>
            </div>
            <br />
        </div>
    )
}


export default NewLogin;