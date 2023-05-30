import React, { useEffect } from 'react';
import s from './ErrorPages.module.css';

import footer_image from '../../assets/images/footer_image.png';

import errorPhoto from '../../assets/images/errorPhoto.png';
import instagram from '../../assets/images/instagram.png';
import twitter from '../../assets/images/twitter.png';

import Header from '../Header/Header';

import axios from 'axios';
import Footer from '../Footer/Footer';





const Error404Page = (props) => {

    useEffect(() => {
        window.scrollTo(0, 0);

        
        // GET TODO
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

       <div className={s.mainWrapper}>
            <h1>404</h1>
            <h2>Сторінку не знайдено!</h2>

            <img src={errorPhoto} alt="" />
       </div>

        <Footer />
        

    </div>
  )
}


export default Error404Page;