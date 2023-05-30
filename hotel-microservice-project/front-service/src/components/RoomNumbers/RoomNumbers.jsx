import React, { useEffect } from 'react';
import s from './RoomNumbers.module.css';


import mainBanner from '../../assets/images/mainBanner.png';
import iconLocation from '../../assets/images/iconLocation.png';
import iconCalendar from '../../assets/images/iconCalendar.png';
import iconMoney from '../../assets/images/iconMoney.png';
import iconPersonal from '../../assets/images/iconPersonal.png';


import photoRoom_1 from '../../assets/images/photoRoom_1.png';
import photoRoom_2 from '../../assets/images/photoRoom_2.png';
import photoRoom_3 from '../../assets/images/photoRoom_3.png';
import photoRoom_4 from '../../assets/images/photoRoom_4.png';
import photoRoom_5 from '../../assets/images/photoRoom_5.png';
import photoRoom_6 from '../../assets/images/photoRoom_6.png';
import iconStars_5 from '../../assets/images/iconStars_5.png';


import Footer from '../Footer/Footer';
import Header from '../Header/Header';
import { Link, NavLink } from 'react-router-dom';


import * as yup from 'yup';
import { Formik } from 'formik';
import DatePicker from "react-datepicker";


import axios from 'axios';
import { AxiosHeaders } from 'axios';





const RoomNumbers = (props) => {

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








      const validationsSchemaBookingRoom = yup.object().shape({

        minPrice: yup.string().typeError('Повинен бути текст').required(`Всі поля обов’язкові!`),
        dateStart: yup.date().nullable().required(`Дата в'їзду обов'язкова`),
        dateEnd: yup.date().nullable().required(`Дата виїзду обов'язкова`),
        maxPrice: yup.string().typeError('Повинен бути текст').required(`Всі поля обов’язкові!`),
        amountOfPerson: yup.string().typeError('Повинен бути текст').required(`Всі поля обов’язкові!`)
        
      })
    
    
      const bookingRoomFunc = (values) => {
        alert("Data: \n" + 
        "Date start: " + values.dateStart + '\n' +
        "Date end: " + values.dateEnd + '\n' +
        "minPrice: " + values.minPrice + '\n' +
        "maxPrice: " + values.maxPrice +'\n' + 
        "Amount of person: " + values.amountOfPerson +'\n' + 
        "City: " + values.selectedCity);
        
        
        // -------------------  TODO ----------------------------
        axios.post('/find-rooms', { 
                dateStart: values.dateStart,
                dateEnd: values.dateEnd,
                minPrice: values.minPrice,
                maxPrice: values.maxPrice,
                amountOfPerson: values.amountOfPerson, 
                selectedCity: values.selectedCity,
            })
            .then(function (response) {
                console.log(response);
            })
            .catch(function (error) {
                console.log(error);
            }
        );

      }


        

  return (
    <div>

       <Header />


        <div className={s.bannerBlock}>
            <img src={mainBanner} alt="" />
            <h2>Нехай <br /> 
                ваша подорож почнеться з <br />
                комфорту з мережою <br />
                готелів "Україна"</h2>
        </div>



        <Formik
                initialValues={{
                    selectedCity: 'kyiv',
                    dateStart: '',
                    dateEnd: '',
                    minPrice: '',
                    maxPrice: '',
                    amountOfPerson: '',                    
                }}
                validateOnBlur
                onSubmit={(values) => bookingRoomFunc(values)}
                validationSchema={validationsSchemaBookingRoom}
            >
            {({ values, errors, touched, handleChange, handleBlur, setFieldValue, handleSubmit, isValid, dirty }) => (
            <div className={s.formWrapper}>            
            <form className={s.formBookingRoom}>
                <div className={s.formBookingRoomInputWrapper}>
                    <img src={iconLocation} alt="" />
                    <select name="selectedCity" onChange={handleChange}>
                            <option value="kyiv" selected>Київ</option>
                            <option value="khmelnytskyi">Хмельницький</option>
                            <option value="zhytomyr">Житомир</option>
                            <option value="lviv">Львів</option>
                            <option value="ternopil">Тернопіль</option>
                            <option value="chernivtsi">Чернівці</option>
                            <option value="rivne">Рівне</option>
                            <option value="lutsk">Луцьк</option>
                            <option value="ivanoFrankivsk">Івано-Франківськ</option>
                            <option value="uzhhorod">Ужгород</option>
                            <option value="vinnitsa">Вінниця</option>
                            <option value="chernihiv">Чернігів</option>
                            <option value="cherkasy">Черкаси</option>  
                            <option value="sumy">Суми</option>
                            <option value="poltava">Полтава</option>
                            <option value="kirovohrad">Кіровоград</option>
                            <option value="odesa">Одеса</option>
                            <option value="mykolaiv">Миколаїв</option>
                            <option value="dnipropetrovsk">Дніпропетровськ</option>
                            <option value="kharkiv">Харків</option>
                            <option value="kherson">Херсон</option>
                            <option value="zaporizhzhia">Запоріжжя</option>
                            <option value="donetsk">Донецьк</option>
                            <option value="luhansk">Луганськ</option>
                            <option value="crimea">Крим</option>
                        </select>
                </div>

                <div className={s.formBookingRoomInputWrapper}>
                    <img src={iconCalendar} alt="" />

                    <DatePicker
                      selected={values.dateStart}
                      dateFormat="MM.dd.yyyy"
                      className="form-control"
                      name="dateStart"
                      onChange={date => setFieldValue('dateStart', date)}
                    />

                    <DatePicker
                      selected={values.dateEnd}
                      dateFormat="MM.dd.yyyy"
                      className="form-control"
                      name="dateEnd"
                      onChange={date => setFieldValue('dateEnd', date)}
                    />
                </div>

                <div className={s.formBookingRoomInputWrapper}>
                    <img src={iconMoney} alt="" />
                    <input type="text" name="minPrice" onChange={handleChange}/>
                    <input type="text" name="maxPrice" onChange={handleChange}/>
                </div>

                <div className={s.formBookingRoomInputWrapper}>
                    <img src={iconPersonal} alt="" />
                    <input type="text" name="amountOfPerson"  onChange={handleChange}/>
                </div>

                

                <div className={s.buttonToFind} onClick={handleSubmit}>Пошук</div>
            </form>


            <span className={s.errorMessageForm}>
            { touched.dateStart && errors.dateStart || touched.dateEnd && errors.dateEnd || 
                touched.minPrice && errors.minPrice || touched.maxPrice && errors.maxPrice 
                || touched.amountOfPerson && errors.amountOfPerson && 
                <span > Всі поля обов’язкові! </span> }
            </span>

            </div>

            
            
            )}
        </Formik>





        <div className={s.roomsInHotelWrapper}>
            <h2>Номери</h2>
            <h4>Готель пропонує гостям різноманіття номерів, починаючи від категорії «Стандарт» до «Президентського»</h4>

            <div className={s.roomsWrapper}>
                <div className={s.room_1}>
                    <img className={s.photoRoom} src={photoRoom_1} alt="" />
                    <img className={s.iconStars} src={iconStars_5} alt="" />
                    <span className={s.descriptionOfRoom} >Номер з ліжком розміру king-size і балконом</span>
                    <span className={s.priceOfRoom} >Ціна: 3 300 грн</span>

                    <Link to='/rooms-numbers/1' preventScrollReset={true} activeClassName={s.activeLink} ><div className={s.buttonToReserve}>Забронювати</div></Link>
                </div>

                <div className={s.room_2}>
                    <img className={s.photoRoom} src={photoRoom_2} alt="" />
                    <img className={s.iconStars} src={iconStars_5} alt="" />
                    <span className={s.descriptionOfRoom} >Номер-студіо з гідромасажною ванною</span>
                    <span className={s.priceOfRoom} >Ціна: 1 400 грн</span>

                    <Link to="/rooms-numbers/2" preventScrollReset={true} activeClassName={s.activeLink} ><div className={s.buttonToReserve} >Забронювати</div></Link>
                </div>

                <div className={s.room_3} >
                    <img className={s.photoRoom} src={photoRoom_3} alt="" />
                    <img className={s.iconStars} src={iconStars_5} alt="" />
                    <span className={s.descriptionOfRoom} >Люкс з гідромасажною ванною</span>
                    <span className={s.priceOfRoom} >Ціна: 1 500 грн</span>

                    <Link to="/rooms-numbers/3" preventScrollReset={true} activeClassName={s.activeLink} ><div className={s.buttonToReserve} >Забронювати</div></Link>
                </div>
            </div>



            <div className={s.roomsWrapper}>
                <div className={s.room_1}>
                    <img className={s.photoRoom} src={photoRoom_4} alt="" />
                    <img className={s.iconStars} src={iconStars_5} alt="" />
                    <span className={s.descriptionOfRoom} >Номер з ліжком розміру king-size і балконом</span>
                    <span className={s.priceOfRoom} >Ціна: 3 300 грн</span>

                    <Link to="/rooms-numbers/4" preventScrollReset={true} activeClassName={s.activeLink} ><div className={s.buttonToReserve} >Забронювати</div></Link>
                </div>

                <div className={s.room_2}>
                    <img className={s.photoRoom} src={photoRoom_5} alt="" />
                    <img className={s.iconStars} src={iconStars_5} alt="" />
                    <span className={s.descriptionOfRoom} >Номер-студіо з гідромасажною ванною</span>
                    <span className={s.priceOfRoom} >Ціна: 1 400 грн</span>

                    <Link to="/rooms-numbers/5" preventScrollReset={true} activeClassName={s.activeLink} ><div className={s.buttonToReserve} >Забронювати</div></Link>
                </div>

                <div className={s.room_3} >
                    <img className={s.photoRoom} src={photoRoom_6} alt="" />
                    <img className={s.iconStars} src={iconStars_5} alt="" />
                    <span className={s.descriptionOfRoom} >Люкс з гідромасажною ванною</span>
                    <span className={s.priceOfRoom} >Ціна: 1 500 грн</span>

                    <Link to="/rooms-numbers/6" preventScrollReset={true} activeClassName={s.activeLink} ><div className={s.buttonToReserve} >Забронювати</div></Link>
                </div>
            </div>


            <Link to="/rooms-numbers/create-new-room" preventScrollReset={true} activeClassName={s.activeLink} ><div className={s.buttonAddNewRoom}>Додати номер</div></Link>
        </div>






    

        <Footer />
        

    </div>
  )
}


export default RoomNumbers;