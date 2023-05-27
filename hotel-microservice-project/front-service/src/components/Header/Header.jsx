import React, { useEffect, useState } from 'react';
import s from './Header.module.css';


import iconGlobus from '../../assets/images/iconGlobus.png';
import iconHotel from '../../assets/images/iconHotel.png';
import { NavLink, unstable_HistoryRouter, useHistory } from 'react-router-dom';
import ModalRegAuth from './Modals/ModalRegAuth';


import axios from 'axios';






const customStyles = {

    overlay: {
      position: 'fixed',
      overflow: 'auto',
      top: 0,
      left: 0,
      right: 0,
      bottom: 0,
      backgroundColor: 'rgba(0, 0, 0, 0.85)',
      zIndex: '9999',
      
    },
    content: {
      top: '50%',
      left: '50%',
      right: 'auto',
      bottom: 'auto',
      marginRight: '-50%',
      transform: 'translate(-50%, -50%)',
      border: '1px solid #875897',
      background: '#fff',
      overflowY: 'auto',
      height: "79%",
      WebkitOverflowScrolling: 'touch',
      borderRadius: '30px',
      outline: 'none',
      padding: '40px',
      zIndex: '9999'
    },
  };


const Header = (props) => {


    useEffect(() => {
        window.scrollTo(0, 0);
      }, []);




    const [errorText, setErrorText] = useState('')
    const [registrationFormIsOpen, setRegistrationFormIsOpen] = React.useState(false);
    const [loginFormIsOpen, setLoginFormIsOpen] = React.useState(false);

    let openRegistrationForm = () => {
        setRegistrationFormIsOpen(true);
        document.body.style.overflow = 'hidden';
    }
    let closeRegistrationForm = () => {
      setRegistrationFormIsOpen(false);
      document.body.style.overflow = 'unset';
    }

    let openLoginForm = () => {
        setLoginFormIsOpen(true);
        document.body.style.overflow = 'hidden';
      }
    let closeLoginForm = () => {
        setLoginFormIsOpen(false);
        document.body.style.overflow = 'unset';
    }






    const onAuth = (values) => {
        alert("Your data: \n" + 
              "Email: " + values.mail + "\n" + 
              "Password: " + values.password
        );
        
        document.body.style.overflow = 'unset';


        // -------------------  TODO ----------------------------
        axios.post('/auth', { 
              mail: values.mail,
              password: values.password,
            })
            .then(function (response) {
                console.log(response);
            })
            .catch(function (error) {
                console.log(error);
            }
        );
    }

    const onReg = (values) => {
        alert("Your data: \n" + 
              "Name: " + values.name + "\n" + 
              "Surname: " + values.surname + "\n" + 
              "Nickname: " + values.nickname + "\n" +
              "Mail: " + values.mail + "\n" +
              "YearsOld: " + values.yearsOld + "\n" +
              "Phone: " + values.phone + "\n" +
              "Password: " + values.password + "\n" +
              "Confirm Password: " + values.confirmPassword
        );
       
        document.body.style.overflow = 'unset';


        // -------------------  TODO ----------------------------
        axios.post('/auth', { 
              name: values.name,
              surname: values.surname,
              nickname: values.nickname,
              mail: values.mail,
              yearsOld: values.yearsOld,
              phone: values.phone,
              password: values.password,
              confirmPassword: values.confirmPassword,
            })
            .then(function (response) {
                console.log(response);
            })
            .catch(function (error) {
                console.log(error);
            }
        );
      }





    let afterOpenLoginForm = () => {}
    let afterOpenRegistrationForm = () => {}





  



  return (

       <header>
            <div className={s.topHeader}>
                <div className={s.infoBlock}>
                    <span>123-456-7890</span>  
                    <span>вул. Перемоги 1</span>
                </div>



            <div className={s.languageBlock}>
                <img src={iconGlobus} alt="" />
                <span>EN</span>
                <span>UK</span>
                </div>
            </div>

            <div className={s.bottomHeader}>
                <div>
                    <img src={iconHotel} alt="" />
                    <h2>ГОТЕЛЬ <br />
                        "УКРАЇНА"</h2>
                </div>

                <nav>
                    <NavLink to="/" activeClassName={s.activeLink} >Головна</NavLink>
                    <NavLink to="/hotel" activeClassName={s.activeLink} >Готель</NavLink>
                    <NavLink to="/rooms-numbers" activeClassName={s.activeLink} >Номери</NavLink>
                    <NavLink to="/contact" activeClassName={s.activeLink} >Контакти</NavLink>


                    <NavLink to="/profile" activeClassName={s.activeLink} >Особистий кабінет</NavLink>

                
                    <i><a href="#" onClick={openLoginForm} className={s.loginLink}>Вхід</a></i>
                    <i><a href="#"onClick={openRegistrationForm}  className={s.registrationLink}>Реєстрація</a></i>
                </nav>
            </div>



            <ModalRegAuth
                type='auth'
                isOpen={loginFormIsOpen}
                onAfterOpen={afterOpenLoginForm}
                onRequestClose={closeLoginForm}
                style={customStyles}
                contentLabel="Example Modal"
                funcClick={onAuth}
                errorText={errorText}
            />


            <ModalRegAuth
                type='fastReg'
                isOpen={registrationFormIsOpen}
                onAfterOpen={afterOpenRegistrationForm}
                onRequestClose={closeRegistrationForm}
                style={customStyles}
                funcClick={onReg}
                contentLabel="Example Modal"
            />

            
        </header>

  )
}


export default Header;