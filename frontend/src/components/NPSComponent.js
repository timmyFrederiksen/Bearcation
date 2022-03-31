import React from 'react';

import ReactDOM from 'react-dom';
import {Route, BrowserRouter, Routes, NavLink, Link} from "react-router-dom";
import Park from '../models/Park';
import NPSService from '../services/NPSService';
import UserService from '../services/UserService';

class NPSComponent extends React.Component {

    constructor(props) {
        super(props);
        this.state = {parks: []};
    }

    componentDidMount(){
        let parkdata = []
        NPSService.getParkInfo().then((response) => {
            response.data.data.map(park => parkdata.push(new Park(park.fullName, park.activities, park.entranceFee, park.latitude, park.longitude)));
            this.setState({parks: parkdata});
        });
    }

    render() {
        return (
            <div>
                <div>
                    <h1>National Park Service Data</h1>
                </div>
                {<div>{this.state.parks.length}</div>}
                {this.state.parks?.map((park) => (<div>{park.fullName}</div>))}
            </div>
           
        );
    }
}

export default NPSComponent;