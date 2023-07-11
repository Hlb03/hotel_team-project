import {React, useEffect, useRef, useState} from 'react';
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

    const [listOfCities, setListOfCities] = useState(
        [
            {   
                name: "Україна",
                location: "kyiv",
                locationName: "Київ"
            },
            {
                name: "Україна",
                location: "khmelnytskyi",
                locationName: "Хмельницький"
            },
            {   
                name: "Україна",
                location: "zhytomyr",
                locationName: "Житомир"
            }
        ]
    );


    useEffect(() => {
        window.scrollTo(0, 0);

        
        // THIS IS THE LIST OF ALL
        axios.get('http://localhost:8080/hotel-rent/hotels', {
            headers: {
                'Content-Type': 'application/json',
            }
            }).then(response => {
                console.log(response.data[0].name + ` (${response.data[0].locationName})`);
                console.log(response.data[0].location) // This is location id, that send to backend while creating a new room. Name it `location`
            
                
                setListOfCities(response.data);
            }).catch(error => {
                console.error(error);
            }
        );
    }, []);









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



  const createNewRoomFunc = (values) => {
    alert("Data: \n" + 
    "Price: " + values.price + '\n' +
    "Short Description: " + values.shortDescription + '\n' +
    "Long Description: " + values.longDescription + '\n' +
    "Photo: " + values.photoRoom.name +'\n' + 
    "Photo type: " + values.photoRoom.type +'\n' + 
    "Photo size: " + values.photoRoom.size +'\n' + 
    "City: " + values.selectedCity);


    // NEW FIELD IS NEEDED WITH NAME 'amountOfPerson', validate min value = 1, max value = 5
    axios.post('http://localhost:8080/hotel-rent/rooms', {
        price: values.price,
        shortDescription: values.shortDescription,
        longDescription: values.longDescription,
        photoRoom: values.photoRoom, // values.photoRoom.name - назва фото
        // values.photoRoom.type - тип фото 
        // values.photoRoom.size - розмір фото
    }
        // MOVE THIS TO `data` AND NAME IT AS `location`, it represents hotel id, where new room will be located
        // {params: {selectedCity: values.selectedCity
      // }
    // }
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
                            {listOfCities.map( city => (
                                <option value={city.location}>{city.name} ( {city.locationName} )</option>                    
                            ))}
                        </select>


                    </div>

                    <div className={s.buttonAddNewRoom} onClick={handleSubmit}>Створити</div>
                </form>

            )}
            </Formik>   
        </div>

    
        <Footer />
        

    </div>
  )
}


export default RoomNumbers_1;