// import React, {useCallback, useMemo, useRef, useState} from "react";
// import {Circle, GoogleMap, Marker} from "@react-google-maps/api";
// import { useNavigate } from "react-router-dom";
//
// import '../styles/explore.css'
//
//
// function Locations(name, des, mile){
//     return(
//         <div className="location-card">
//             <h3>{name} {mile} mi</h3>
//         </div>
//     );
// }
//
// function Explore(){
//
//     const [search, setSearch] = useState('');
//
//     return(
//         <div>
//             <h1>Explore Parks</h1>
//
//             <div className="search-group form-group">
//                 <Combobox onSelect={handleSelect}>
//                     <ComboboxInput
//                         value={value}
//                         onChange={(e) => setValue(e.target.value)}
//                         disabled={!ready}
//                         className="combobox-input"
//                         placeholder="Enter a Location:"
//                     />
//                     <ComboboxPopover>
//                         <ComboboxList>
//                             {status === "OK" &&
//                             data.map(({ place_id, description }) => (
//                                 <ComboboxOption key={place_id} value={description} />
//                             ))}
//                         </ComboboxList>
//                     </ComboboxPopover>
//                 </Combobox>
//                 <button name = "advanced-search">Advanced Search</button>
//             </div>
//             <div className="Map">
//
//             </div>
//             <div className="Place">
//                 {Locations('Grand Canyon', 'canyons', 53.1)}
//                 {Locations('Yosemite', 'Cool', 11.1)}
//             </div>
//         </div>
//
//     );
// }
//
// export default Explore;