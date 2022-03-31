import React from "react";
import UserService from "../services/UserService";
import axios from "axios";
import { Link } from "react-router-dom";

class ForgotPassword extends React.Component {
    accounts = [];
    constructor(props) {
        super(props);
        this.state = { username: "", password: "", confirmPassword: "" };
        this.handleInputChange = this.handleInputChange.bind(this);
        this.handleChangeStatus = this.handleChangeStatus.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChangeStatus(event) {
        this.setState({ level: event.target.value });
    }

    handleInputChange(event) {
        const target = event.target;
        const value = target.type === "checkbox" ? target.checked : target.value;
        const name = target.name;

        this.setState({
            [name]: value,
        });
    }

    componentDidMount() {
        UserService.getUsers().then((response) => {
            this.accounts = response.data;
        });
    }

    handleSubmit(event) {
        const userDto = {
            password: this.state.password,
            username: this.state.username,
        };

        axios.get("http://localhost:80/user/users", userDto).then((res) => {
            console.log("Password changed successfully!");
            console.log(res);
            this.setState({
                username: res.data.username,
                password: res.data.password,
            });
            alert("The password for " + this.state.username + " has changed.");
        });
        event.preventDefault();
    }

    render() {
        return (
            <div>
                <h1>Forgot Password</h1>
                <form onSubmit={this.handleSubmit}>
                    <div className="reset-form-group">
                        <label>Username:</label>
                        <input
                            name="username"
                            className="form-control"
                            value={this.state.username}
                            type="text"
                            onChange={this.handleInputChange}
                        />
                    </div>
                    <div>
                        <label>New Password:</label>
                        <input
                            name="password"
                            value={this.state.password}
                            type="text"
                            onChange={this.handleInputChange}
                        />
                    </div>
                    <div>
                        <label>Confirm New Password:</label>
                        <input
                            name="confirmPassword"
                            value={this.state.confirmPassword}
                            type="text"
                            onChange={this.handleInputChange}
                        />
                    </div>
                    <input type="button" value="Submit" onChange={this.handleSubmit} />
                </form>
                <br />
                <label>{this.state.message}</label>
            </div>
        );
    }
}

export default ForgotPassword;
