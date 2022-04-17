import React, {useCallback, useMemo, useRef, useState} from "react";
import { useLoadScript, Circle, GoogleMap, Marker} from "@react-google-maps/api";
import { useNavigate } from "react-router-dom";

import usePlacesAutocomplete, {
    getGeocode,
    getLatLng,
} from 'use-places-autocomplete';
import {
    Combobox,
    ComboboxInput,
    ComboboxPopover,
    ComboboxList,
    ComboboxOption,
} from "@reach/combobox";
import "@reach/combobox/styles.css";
import '../styles/explore.css'
import Places from "./Places";

const grandCanyon = {
    'name': 'Grand Canyon', 
    'description': 'canyons', 
    'distance': 53.1
}


function PlaceCard(name, description, distance){
    return(
        <div className="location-card">
            <h3>{name} {distance} mi</h3>
        </div>
    );
}



function Explore(){

    const [vacationLocation, setVacationLocation] = useState();
    const [places, setPlaces] = useState([]);

    const mapRef = useRef();
    const center = useMemo(
        () => ({ lat: 31.5489, lng: -97.1131 }),
        []
    );


    const onLoad = useCallback((map) => (mapRef.current = map), [])
    const { isLoaded } = useLoadScript({
        googleMapsApiKey: "AIzaSyA3kz9oH9nUDtfo8K4xpks2-KVkP26-IKo",
        libraries: ["places"],
    });

    const searchTopPlaces = async () => {
        //const response = await fetch(`${API_URL}&s=${title}`);
        //const data = await response.json();
    
        //setPlaces(data); data.{}
    };

    if(!isLoaded) return <div>Loading...</div>
    return(
        <div className="explore-body">
            <h1>Explore Parks</h1>
            <div className="search-form">
                <div className="search-group form-group">
                    <Places className="search-text" setVacationLocation={(position) => {
                        setVacationLocation(position);
                        mapRef.current?.panTo(position);
                    }}/>
                    <button className="advanced-search">Advanced Search</button>
                </div>
                <div className="map-group">
                    <GoogleMap
                        zoom={10}
                        center={center}
                        mapContainerClassName="map-container"
                        onLoad={onLoad}
                    >
                        {vacationLocation && (
                        <>
                            <Marker 
                                position={vacationLocation}
                                icon="http://maps.google.com/mapfiles/kml/paddle/blu-circle.png"
                            />

                            {/* <Circle center={vacationLocation} radius={85000} options={closeOptions} />
                            <Circle center={vacationLocation} radius={160934} options={middleOptions} />
                            <Circle center={vacationLocation} radius={402336} options={farOptions} />
                            <Circle center={vacationLocation} radius={1207000} options={superFarOptions} /> */}
                        </>
                        )}
                    </GoogleMap>
                </div>
                <div className="places-group">
                    <h1>Parks:</h1>
                    <div className="location-group">
                        {/* {places.map((place) => (
                            <PlaceCard place={place} />
                        ))} */}
                        {PlaceCard(grandCanyon.name, grandCanyon.description, grandCanyon.distance)}
                    </div>
                </div>
            </div>
        </div>
    );
} 

export default Explore;
