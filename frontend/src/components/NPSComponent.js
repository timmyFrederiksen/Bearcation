import React from "react";
import Park from "../models/Park";
import NPSService from "../services/NPSService";

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

    calculateDistance(slat1, long1, slat2, long2) {
        let R = 3950; // km
        let dLat = this.toRad(parseFloat(slat2) - parseFloat(slat1));
        let dLong = this.toRad(parseFloat(long2) - parseFloat(long1));
        let lat1 = this.toRad(slat1);
        let lat2 = this.toRad(slat2);

        let a = Math.sin(dLat/2) * Math.sin(dLat/2) +
            Math.sin(dLong/2) * Math.sin(dLong/2) * Math.cos(lat1) * Math.cos(lat2);
        let c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }

    // Converts numeric degrees to radians
    toRad(value) {
        return parseFloat(value) * Math.PI / 180;
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
        let parkData;
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
                        {park.fullName} | {park.activities} | {park.price} |
                        {this.calculateDistance("31.559814", "-97.141800", park.lat, park.long)}
                    </div>
                ))}
            </div>
        );
    }
}

export default NPSComponent;
