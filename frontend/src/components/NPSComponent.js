import React from "react";

import ReactDOM from "react-dom";
import { Route, BrowserRouter, Routes, NavLink, Link } from "react-router-dom";
import Park from "../models/Park";
import NPSService from "../services/NPSService";
import UserService from "../services/UserService";

class NPSComponent extends React.Component {
    constructor(props) {
        super(props);
        this.state = { parks: [], sortType: 0 };
        this.sortByFee = this.sortByFee.bind(this);
        this.sortByLocation = this.sortByLocation.bind(this);
        this.resetSort = this.resetSort.bind(this);
    }

    componentDidMount() {
        let parkdata = [];
        NPSService.getParkInfo().then((response) => {
            response.data.data.map((park) =>
                parkdata.push(
                    new Park(
                        park.fullName,
                        park.activities.length,
                        park.entranceFees[0].cost,
                        park.latitude,
                        park.longitude
                    )
                )
            );
            this.setState({ parks: parkdata });
        });
    }

    sortByFee() {
        this.setState({ sortType: 1 });
        this.sort();
    }

    sortByLocation() {
        this.setState({ sortType: 2 });
        this.sort();
    }

    resetSort() {
        this.setState({ sortType: 0 });
        this.sort();
    }

    sort() {
        var parkData = this.state.parks;
        switch (this.state.sortType) {
            case 1:
                parkData = [].concat(this.state.parks).sort((a, b) => a.price - b.price);
                break;
            case 2:
                parkData = [].concat(this.state.parks).sort((a, b) => a.lat - b.lat);
                break;
            default:
                parkData = []
                    .concat(this.state.parks)
                    .sort((a, b) => a.fullName.localeCompare(b.fullName));
                break;
        }

        this.setState({ parks: parkData });
    }

    render() {
        return (
            <div>
                <div>
                    <h1>National Park Service Data</h1>
                </div>
                <div>
                    <button onClick={this.sortByFee}>Sort by Fee</button>
                    <button onClick={this.sortByLocation}>Sort by Location</button>
                    <button onClick={this.resetSort}>Sort Alphabetically</button>
                </div>
                {<div>{this.state.parks.length}</div>}
                <div>{this.state.sortType}</div>
                {this.state.parks?.map((park) => (
                    <div>
                        {park.fullName} | {park.activities} | {park.price} | {park.lat} |{" "}
                        {park.long}
                    </div>
                ))}
            </div>
        );
    }
}

export default NPSComponent;
