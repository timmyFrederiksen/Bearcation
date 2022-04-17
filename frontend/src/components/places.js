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
//import "../styles/searchPage.css"
import {Carousel} from "bootstrap";
import {useRef} from "react";
import {GoogleMap} from "@react-google-maps/api";

// type PlacesProps = {
//     setVacationLocation: (position: google.maps.LatLngLiteral) => void;
// };

export default function Places({setVacationLocation}) {
    const {ready, value, setValue, suggestions: {status, data}, clearSuggestions} = usePlacesAutocomplete();

    const handleSelect = async (val) => {
        setValue(val, false);
        clearSuggestions();

        const results = await getGeocode({ address: val });
        const { lat, lng } = await getLatLng(results[0]);
        setVacationLocation({ lat, lng });
    };

    return (
        <Combobox onSelect={handleSelect}>
            <ComboboxInput
                value={value}
                onChange={(e) => setValue(e.target.value)}
                disabled={!ready}
                className="combobox-input"
                placeholder="Enter a Location:"
            />
            <ComboboxPopover>
                <ComboboxList>
                    {status === "OK" &&
                    data.map(({ place_id, description }) => (
                        <ComboboxOption key={place_id} value={description} />
                    ))}
                </ComboboxList>
            </ComboboxPopover>
        </Combobox>
    );
}
