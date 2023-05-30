import React, { useEffect } from 'react';
import s from './HotelPage.module.css';

import descriptionPhoto_1 from '../../assets/images/descriptionPhoto_1.png';
import descriptionPhoto_2 from '../../assets/images/descriptionPhoto_2.png';
import descriptionPhoto_3 from '../../assets/images/descriptionPhoto_3.png';


import Header from '../Header/Header';
import Footer from '../Footer/Footer';

import axios from 'axios';





const HotelPage = (props) => {


    useEffect(() => {
        window.scrollTo(0, 0);


        //GET 
        //TODO
        axios.get('endpointToGetData', {
            headers: {
                'Content-Type': 'application/json',
            }
            }).then(response => {
                console.log(response.data);
            }).catch(error => {
                console.error(error);
            }
        );
    }, []);



    


  return (
    <div>


        <Header />


        <div className={s.descriptionAboutHotelWrapper}>
            <div className={s.descriptionAboutHotelText}>
                <h3>Ласкаво просимо</h3>
                <h2>Готель "Україна"</h2>
                <span>Готель "Україна" - це поєднання унікальності місця, вишуканості стильного дизайну, комфорту і професійного 
                обслуговування. Світлі номери, виконані за авторським дизайном, надають всі умови для роботи та відпочинку.</span>
            </div>

            <div className={s.descriptionAboutHotelImages}>
                <img className={s.descriptionPhoto_1} src={descriptionPhoto_1} alt="" />
                <img className={s.descriptionPhoto_2} src={descriptionPhoto_2} alt="" />
                <img className={s.descriptionPhoto_3} src={descriptionPhoto_3} alt="" />
            </div>
        </div>


        <Footer />
        

    </div>
  )
}


export default HotelPage;