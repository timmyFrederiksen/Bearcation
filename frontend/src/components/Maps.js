import React from "react";
import Home from "./mapCall";

class Maps extends React.Component {
    constructor(props) {
        super(props);
        this.state = {};
    }
    /*handleChangeStatus(event) {
        this.setState({level: event.target.value});
    }

    handleInputChange(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;

        this.setState({
            [name]: value
        });

        this.sort();
    }

    componentDidMount() {
        let parkdata = [];
        NPSService.getParkInfo().then((response) => {
            response.data.data.map((park) =>
                parkdata.push(
                    new Park(
                        park.fullName,
                        park.activities.length,
                        park.activities,
                        (park.entranceFees.length > 0) ? park.entranceFees[0].cost : 0,
                        park.latitude,
                        park.longitude,
                    )
                )
            );
            this.setState({ parks: parkdata });
        });
    }*/

    render() {
        return (
            <div className="search-page">
                <div className="search">
                    Search
                </div>
                <div className="map">
                    <Home />
                </div>
            </div>
        );
    }
}

export default Maps;
