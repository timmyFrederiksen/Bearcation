import React, {useCallback, useMemo, useRef, useState} from "react";
import Home from "./GMap";
import Places from "./places";
import {GoogleMap, Marker} from "@react-google-maps/api";



function Maps() {

    const [vacationLocation, setVacationLocation] = useState();
    const mapRef = useRef();
    const center = useMemo(
        () => ({ lat: 37.5489, lng: -97.1131 }),
        []
    );

    const onLoad = useCallback((map) => (mapRef.current = map), [])

    return (
        <div className="container">
            <div className="controls">
                <h1>Search</h1>
                <Places  setVacationLocation={(position) => {
                    setVacationLocation(position);
                    mapRef.current?.panTo(position);
                }}/>
            </div>
            <div className="map">
                <GoogleMap
                    zoom={10}
                    center={center}
                    mapContainerClassName="map-container"
                    onLoad={onLoad}
                >
                </GoogleMap>
            </div>
        </div>
    );
}

export default Maps;
