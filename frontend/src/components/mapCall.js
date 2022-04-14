import '../styles/maps.css'
import { useName } from "react";
import { GoogleMap, useLoadScript, Marker } from "@react-google-maps/api";



export default function Home() {
    const { isLoaded } = useLoadScript({googleMapsApiKey: "AIzaSyA3kz9oH9nUDtfo8K4xpks2-KVkP26-IKo", })

    if(!isLoaded) return <div>Loading...</div>
    return (<Map />);


}

const center = {lat: 31.5489, lng: -97.1131};

function Map(){
    return (
        <GoogleMap
            zoom={15}
            center={center}
            mapContainerClassName="map-container"
        >
            <Marker position={{lat: 31.5489, lng: -97.1131}} />

        </GoogleMap>
    );
}