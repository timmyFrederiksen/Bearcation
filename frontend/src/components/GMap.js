import { useState, useMemo, useCallback, useRef } from "react";
import { useEffect } from "react";
import {
    GoogleMap,
    useLoadScript,
    Marker,
    DirectionsRenderer,
    Circle,
    MarkerClusterer,
} from "@react-google-maps/api";

import '../styles/maps.css'
import Maps from "./Maps";

export default function Home() {
    const { isLoaded } = useLoadScript({
        googleMapsApiKey: "AIzaSyA3kz9oH9nUDtfo8K4xpks2-KVkP26-IKo",
        libraries: ["places"],
    });

    if(!isLoaded) return <div>Loading...</div>
    return (<Maps />);

}

