import logo from './logo.svg';
import './App.css';
import { BrowserRouter, Route, Routes, useHistory, Switch, Redirect } from "react-router-dom";
import React, { useEffect } from 'react';
import MainPage from './components/MainPage/MainPage';
import RoomNumbers from './components/RoomNumbers/RoomNumbers';
import RoomNumbersPage from './components/RoomNumbers//Numbers/RoomNumbersPage';


import CreateNewRoom from './components/RoomNumbers/Numbers/CreateNewRoom';
import ProfileUser from './components/ProfileUser/ProfileUser';
import ContactPage from './components/ContactPage/ContactPage';
import HotelPage from './components/HotelPage/HotelPage';

import Error403Page from './components/ErrorPages/Error403Page';
import Error404Page from './components/ErrorPages/Error404Page';
import Error500Page from './components/ErrorPages/Error500Page';


function App() {
  return (
      <Routes>
        <Route exact path="/" element={<MainPage />} />
        <Route exact path="/rooms-numbers" element={<RoomNumbers />} />
        <Route exact path="/hotel" element={<HotelPage />} />

        <Route path={'/rooms-numbers/number/'} element={<RoomNumbersPage/>} />

        <Route exact path="/rooms-numbers/create-new-room" element={<CreateNewRoom />} />

        <Route exact path="/profile" element={<ProfileUser tab="booked-rooms"/>} />
        <Route exact path="/profile/personal-data" element={<ProfileUser tab="personal-data"/>} />


        <Route exact path="/contact" element={<ContactPage />} />



        <Route path="/error-403" element={<Error403Page />} />
        <Route path="*" element={<Error404Page />} />
        <Route path="/error-500" element={<Error500Page />} />

      </Routes>
  );
}

export default App;
