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
        var parkData;
        switch (this.state.sortType) {
            case 1:
                parkData = [].concat(this.state.parks).sort((a, b) => a.price - b.price);
                break;
            case 2:
                parkData = [].concat(this.state.parks).sort((a, b) => a.latitude - b.latitude);
                break;
            default:
                parkData = [].concat(this.state.parks).sort((a, b) => a.fullName - b.fullName);
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
                    <form>
                        <input type="button" value="Sort by Fee" onChange={this.sortByFee} />

                        <input
                            type="button"
                            value="Sort by Location"
                            onChange={this.sortByLocation}
                        />

                        <input type="button" value="Sort" onChange={this.resetSort} />
                    </form>
                </div>
                {<div>{this.state.parks.length}</div>}
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
