import React from 'react';
import ReactDOM from 'react-dom';
import UserService from '../services/UserService';
import axios from 'axios';

class HomePage extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div className="HomeDashboard">
                <h1>Home</h1>
            </div>
        );
    }
}

export default HomePage;