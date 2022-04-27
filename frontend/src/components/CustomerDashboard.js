import KeyboardArrowRightIcon from '@mui/icons-material/KeyboardArrowRight';
import IconButton from '@material-ui/core/IconButton';
import TravelExploreIcon from '@mui/icons-material/TravelExplore';

import React, { useCallback, useMemo, useRef, useState } from "react";
import { useNavigate, Link } from "react-router-dom";

import '../styles/customerDashboard.css'
import HeaderBar from "./HeaderBar";
<<<<<<< Updated upstream
=======
import DashboardParkCard from "./DashboardParkCard";
import axios from "axios";
>>>>>>> Stashed changes

const Person = {
    firstName: "Francis",
    lastName: "Boyle"
}
const parkExampleArray = [
    { name: "Alaska National Park" },
    { name: "Utah National Park"},
    { name: "Utah National Park"},
    { name: "Utah National Park"},
    { name: "Utah National Park"}
]
const parkExample = { name: "Alaska National Park" }


function CustomerDashboard() {

    const [vacationLocation, setVacationLocation] = useState();
    const navigate = useNavigate();
<<<<<<< Updated upstream
    
=======
    const location = useLocation();

    const [locations, setLocations] = useState();
    useEffect(async () =>{
        let response;
        await axios.get("http://localhost:80/location/search")
            .then(res => {
                response = res.data;
                console.log(response);
            })
            setLocations(response);
    }, []);

>>>>>>> Stashed changes
    return (
        <div className="customer-dashboard-page">
            <HeaderBar />
            <div className="customer-dashboard-body">
                <div className="customer-dashboard-details">
                    <h1 className="customer-dashboard-welcome-text">
                        <b>Hello, {Person.firstName}!</b>
                    </h1>
                    <Link to="/explore" state={{name: location.state.fName}}>
                        <h2 className="customer-dashboard-explore-text">
                            Explore National Parks and Landmarks!
                        </h2>
                    </Link>
                    <Link to="/">
                        <h2 className="customer-dashboard-settings-text">
                            Edit Settings
                        </h2>
                    </Link>
                </div>
                <div className="customer-dashboard-parks">
                    <h2>View Recommended Parks:</h2>
                    {
<<<<<<< Updated upstream
                        parkExampleArray.length > 0 
                        ? (
                            <div className="customer-dashboard-recommended-parks">
                                {parkExampleArray.map((park) => <DashboardParkCard park={park}/>)}
                            </div>
                        ) : (
                            <div>
                                Sorry, we do not have any recommended parks.
                            </div>
                        )
=======
                        locations
                            ? (
                                <div className="customer-dashboard-recommended-parks">
                                    {locations.map((park) => <DashboardParkCard park={park}/>)}
                                </div>
                            ) : (
                                <div>
                                    Sorry, we do not have any recommended parks.
                                </div>
                            )
>>>>>>> Stashed changes
                    }
                </div>
            </div>
        </div>
    );
}

export default CustomerDashboard;

