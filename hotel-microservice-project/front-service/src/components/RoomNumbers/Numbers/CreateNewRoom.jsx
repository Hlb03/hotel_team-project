import {React, useRef, useState} from 'react';
import s from '../RoomNumbers.module.css';



import photoRoom_1 from '../../../assets/images/photoRoom_1.png';
import photoRoom_2 from '../../../assets/images/photoRoom_2.png';
import photoRoom_3 from '../../../assets/images/photoRoom_3.png';
import photoRoom_4 from '../../../assets/images/photoRoom_4.png';
import photoRoom_5 from '../../../assets/images/photoRoom_5.png';
import photoRoom_6 from '../../../assets/images/photoRoom_6.png';
import iconStars_5 from '../../../assets/images/iconStars_5.png';



import Header from '../../Header/Header';
import Footer from '../../Footer/Footer';
import { Field, ErrorMessage, useFormik, Formik, Form  } from 'formik';


import * as yup from 'yup';
import PreviewImage from './PreviewImage';
import { Link } from 'react-router-dom';


import axios from 'axios';






const RoomNumbers_1 = (props) => {


    const MAX_FILE_SIZE = 102400; //100KB

    const validFileExtensions = { image: ['jpg', 'gif', 'png', 'jpeg', 'svg', 'webp'] };
    const filesharhe_ref = useRef(null);

    function isValidFileType(fileName, fileType) {
        return fileName && validFileExtensions[fileType].indexOf(fileName.split('.').pop()) > -1;
      }

    //Payment Form
  const validationsSchemaCreateNewRoom = yup.object().shape({

    price: yup.string().typeError('Повинен бути текст').required(`Поле обов’язкове`),
    shortDescription: yup.string().typeError('Повинен бути текст').required(`Поле обов’язкове`),
    longDescription: yup.string().typeError('Повинен бути текст').required(`Поле обов’язкове`),
    
  })

  var fs = require("fs");


  const createNewRoomFunc = (values) => {
    alert("Data: \n" + 
    "Price: " + values.price + '\n' +
    "Short Description: " + values.shortDescription + '\n' +
    "Long Description: " + values.longDescription + '\n' +
    "Photo: " + values.photoRoom.name +'\n' + 
    "Photo type: " + values.photoRoom.type +'\n' + 
    "Photo size: " + values.photoRoom.size +'\n' + 
    "City: " + values.selectedCity);


    // selectedCity argument is in params, because backend expects an id of city, not the name
    axios.post('http://localhost:8080/hotel-rent/rooms', {
        price: values.price,
        shortDescription: values.shortDescription,
        longDescription: values.longDescription,
        photoRoom: values.photoRoom, // values.photoRoom.name - назва фото
        // values.photoRoom.type - тип фото 
        // values.photoRoom.size - розмір фото
    }, { params: {selectedCity: values.selectedCity
      }}
    )
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






        
        <div className={s.formToAddNewRoomWrapper}>
          

            <Formik
                initialValues={{
                    price: '',
                    shortDescription: '',
                    longDescription: '',
                    photoRoom: null,
                    selectedCity: 'kyiv',
                }}
                validateOnBlur
                onSubmit={(values) => createNewRoomFunc(values)}
                validationSchema={validationsSchemaCreateNewRoom}
            >
            {({ values, errors, touched, handleChange, handleBlur, setFieldValue, handleSubmit, isValid, dirty }) => (
                                    
                <form className={s.formToAddNewRoom}>
                    <h3>Форма добавлення нового номеру</h3>

                    <div className={s.priceFieldWrapper}>
                        <label htmlFor="">Ціна:</label>
                        <span>₴</span>
                        <input type={`text`} 
                                name={`price`}
                                onChange={handleChange}
                                onBlur={handleBlur}
                                value={values.price}  
                        />

                    </div>
                    { touched.price && errors.price && <span className={s.errorMessageForm}>{errors.price}</span> }


                    <div className={s.shortDescriptiondWrapper}>
                        <label htmlFor="">Короткий опис:</label>
                        <input type={`text`} 
                                name={`shortDescription`}
                                onChange={handleChange}
                                onBlur={handleBlur}
                                value={values.shortDescription} 
                        />
                    </div>
                    { touched.shortDescription && errors.shortDescription && <span className={s.errorMessageForm}>{errors.shortDescription}</span> }

                    <div className={s.longDescriptiondWrapper}>
                        <label htmlFor="">Довгий опис:</label>
                        <textarea type={`text`} 
                                name={`longDescription`}
                                onChange={handleChange}
                                onBlur={handleBlur}
                                value={values.longDescription} 
                        />
                    </div>
                    { touched.longDescription && errors.longDescription && <span className={s.errorMessageForm}>{errors.longDescription}</span> }

                    <div className={s.photoOfRoomdWrapper}>
                        <label htmlFor="">Фото номеру:</label>
                        <input type="file" innerRef={filesharhe_ref}  id="img" accept="image/*" name="photoRoom" onChange={(e) => 
                            setFieldValue('photoRoom', e.currentTarget.files[0])} 

                            
                        />
                    </div>
                    { errors.photoRoom && <span className={s.errorMessageForm}>{errors.longDescription}</span> }
                    { values.photoRoom && <PreviewImage photoRoom={values.photoRoom} /> }
                    {/* <PreviewImage file={values.photoRoom} /> */}


                    <div className={s.selectHotelWrapper}>
                        <label>Виберіть готель в якому знаходиться номер:</label>
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

                    <div className={s.buttonAddNewRoom} onClick={handleSubmit}>Створити</div>
                </form>

            )}
            </Formik>   
        </div>









        <div className={s.roomsInHotelWrapper}>
            <h2>Номери</h2>
            <h4>Готель пропонує гостям різноманіття номерів, починаючи від категорії «Стандарт» до «Президентського»</h4>

            <div className={s.roomsWrapper}>
                <div className={s.room_1}>
                    <img className={s.photoRoom} src={photoRoom_1} alt="" />
                    <img className={s.iconStars} src={iconStars_5} alt="" />
                    <span className={s.descriptionOfRoom} >Номер з ліжком розміру king-size і балконом</span>
                    <span className={s.priceOfRoom} >Ціна: 3 300 грн</span>

                    <Link to='/rooms-numbers/1' preventScrollReset={true} activeClassName={s.activeLink} ><div className={s.buttonToReserve} >Забронювати</div></Link>
                </div>

                <div className={s.room_2}>
                    <img className={s.photoRoom} src={photoRoom_2} alt="" />
                    <img className={s.iconStars} src={iconStars_5} alt="" />
                    <span className={s.descriptionOfRoom} >Номер-студіо з гідромасажною ванною</span>
                    <span className={s.priceOfRoom} >Ціна: 1 400 грн</span>

                    <Link to='/rooms-numbers/2' preventScrollReset={true} activeClassName={s.activeLink} ><div className={s.buttonToReserve} >Забронювати</div></Link>
                </div>

                <div className={s.room_3} >
                    <img className={s.photoRoom} src={photoRoom_3} alt="" />
                    <img className={s.iconStars} src={iconStars_5} alt="" />
                    <span className={s.descriptionOfRoom} >Люкс з гідромасажною ванною</span>
                    <span className={s.priceOfRoom} >Ціна: 1 500 грн</span>

                    <Link to='/rooms-numbers/3' preventScrollReset={true} activeClassName={s.activeLink} ><div className={s.buttonToReserve} >Забронювати</div></Link>
                </div>
            </div>



            <div className={s.roomsWrapper}>
                <div className={s.room_1}>
                    <img className={s.photoRoom} src={photoRoom_4} alt="" />
                    <img className={s.iconStars} src={iconStars_5} alt="" />
                    <span className={s.descriptionOfRoom} >Номер з ліжком розміру king-size і балконом</span>
                    <span className={s.priceOfRoom} >Ціна: 3 300 грн</span>

                    <Link to='/rooms-numbers/4' preventScrollReset={true} activeClassName={s.activeLink} ><div className={s.buttonToReserve} >Забронювати</div></Link>
                </div>

                <div className={s.room_2}>
                    <img className={s.photoRoom} src={photoRoom_5} alt="" />
                    <img className={s.iconStars} src={iconStars_5} alt="" />
                    <span className={s.descriptionOfRoom} >Номер-студіо з гідромасажною ванною</span>
                    <span className={s.priceOfRoom} >Ціна: 1 400 грн</span>

                    <Link to='/rooms-numbers/5' preventScrollReset={true} activeClassName={s.activeLink} ><div className={s.buttonToReserve} >Забронювати</div></Link>
                </div>

                <div className={s.room_3} >
                    <img className={s.photoRoom} src={photoRoom_6} alt="" />
                    <img className={s.iconStars} src={iconStars_5} alt="" />
                    <span className={s.descriptionOfRoom} >Люкс з гідромасажною ванною</span>
                    <span className={s.priceOfRoom} >Ціна: 1 500 грн</span>

                    <Link to='/rooms-numbers/6' preventScrollReset={true} activeClassName={s.activeLink} ><div className={s.buttonToReserve} >Забронювати</div></Link>
                </div>
            </div>


            <div className={s.buttonAddNewRoom}>Додати номер</div>
        </div>






    
<Footer />
        

    </div>
  )
}


export default RoomNumbers_1;