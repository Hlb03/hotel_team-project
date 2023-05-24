import logo from './logo.svg';
import './App.css';
import { BrowserRouter, Route, Routes, useHistory, Switch } from "react-router-dom";
import React, { useEffect } from 'react';
import MainPage from './components/MainPage/MainPage';
import RoomNumbers from './components/RoomNumbers/RoomNumbers';

import RoomNumbers_1 from './components/RoomNumbers/Numbers/RoomNumbers_1';
import RoomNumbers_2 from './components/RoomNumbers/Numbers/RoomNumbers_2';
import RoomNumbers_3 from './components/RoomNumbers/Numbers/RoomNumbers_3';
import RoomNumbers_4 from './components/RoomNumbers/Numbers/RoomNumbers_4';
import RoomNumbers_5 from './components/RoomNumbers/Numbers/RoomNumbers_5';
import RoomNumbers_6 from './components/RoomNumbers/Numbers/RoomNumbers_6';

import CreateNewRoom from './components/RoomNumbers/Numbers/CreateNewRoom';
import ProfileUser from './components/ProfileUser/ProfileUser';
import ContactPage from './components/ContactPage/ContactPage';
import HotelPage from './components/HotelPage/HotelPage';


function App() {
  return (
      <Routes>
        <Route path="/" element={<MainPage />} />
        <Route path="/rooms-numbers" element={<RoomNumbers />} />
        <Route path="/hotel" element={<HotelPage />} />

        <Route path="/rooms-numbers/1" element={<RoomNumbers_1 />} />
        <Route path="/rooms-numbers/2" element={<RoomNumbers_2 />} />
        <Route path="/rooms-numbers/3" element={<RoomNumbers_3 />} />
        <Route path="/rooms-numbers/4" element={<RoomNumbers_4 />} />
        <Route path="/rooms-numbers/5" element={<RoomNumbers_5 />} />
        <Route path="/rooms-numbers/6" element={<RoomNumbers_6 />} />

        <Route path="/rooms-numbers/create-new-room" element={<CreateNewRoom />} />

        <Route path="/profile" element={<ProfileUser tab="booked-rooms"/>} />
        <Route path="/profile/personal-data" element={<ProfileUser tab="personal-data"/>} />


        <Route path="/contact" element={<ContactPage />} />
      </Routes>
  );
}

export default App;
