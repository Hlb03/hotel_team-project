import React, { useEffect, useState } from 'react';
import s from './ProfileUser.module.css';



import photoRoom_1 from '../../assets/images/photoRoom_1.png';
import photoRoom_2 from '../../assets/images/photoRoom_2.png';
import photoRoom_3 from '../../assets/images/photoRoom_3.png';
import photoRoom_4 from '../../assets/images/photoRoom_4.png';
import photoRoom_5 from '../../assets/images/photoRoom_5.png';
import photoRoom_6 from '../../assets/images/photoRoom_6.png';
import iconStars_5 from '../../assets/images/iconStars_5.png';


import { NavLink, Route, Routes } from 'react-router-dom';
import Header from '../Header/Header';
import Footer from '../Footer/Footer';


import star1 from '../../assets/images/star1.png';
import star2 from '../../assets/images/star2.png';
import star3 from '../../assets/images/star3.png';
import star4 from '../../assets/images/star4.png';
import star5 from '../../assets/images/star5.png';



import { Formik } from 'formik';
import * as yup from 'yup';

import axios from 'axios';





const ProfileUser = (props) => {

    const [name, setName] = useState('Anton');
    const [surname, setSurname] = useState("Sadlovskiy");
    const [nickname, setNickname] = useState("antonsadlovskiy");
    const [mail, setMail] = useState("antonsadlov@gmail.com");
    const [yearsOld, setYearsOld] = useState("20");
    const [phone, setPhone] = useState("0960175470");
    const [balance, setBalance] = useState(3000);
    const [password, setPassword] = useState("A12345678");


    

    const [amountBookedRooms, setAmountBookedRooms] = useState([1]);
    const [images, setImages] = useState([photoRoom_1, photoRoom_2, photoRoom_3, photoRoom_4, photoRoom_5, photoRoom_6]);

    const [startRent, setStartRent] = useState(["14.03.2023"]);
    const [endRent, setEndRent] = useState(["20.03.2023"]);
    const [price, setPrice] = useState([3200]);
    const [shortDescription, setShortDescription] = useState(["Номер з ліжком розміру king-size і балконом", "Номер-студіо з гідромасажною ванною", "Люкс з гідромасажною ванною"]);
    const [rate, setRate] = useState([2,4,1]);
    const [amountOfPerson, setAmountOfPerson] = useState([3,4,2]);
    
    



    // request for user credentials
    useEffect(() => {
        window.scrollTo(0, 0);

        axios.get('http://localhost:8080/hotel-rent/users', {
            headers: {
                'Content-Type': 'application/json',
            }
            }).then(response => {
                console.log(response.data.name);
                console.log(response.data.surname);
                console.log(response.data.nickname);
                console.log(response.data.mail);
                console.log(response.data.yearsOld);
                console.log(response.data.phone);
                console.log(response.data.balance);
                console.log(response.data.password); // this don't put into form. just leave default value for all users
            
                
                setName(response.data.name);
                setSurname(response.data.surname);
                setNickname(response.data.nickname);
                setMail(response.data.mail);
                setYearsOld(response.data.yearsOld);
                setPhone(response.data.phone);
                setBalance(response.data.balance);
                setPassword(response.data.password);
            
            }).catch(error => {
                console.error(error);
            }
        );
    }, []);

    // request for user booked rooms
    useEffect(() => {
        window.scrollTo(0, 0);

        axios.get('http://localhost:8080/hotel-rent/user-rooms', {
            headers: {
                'Content-Type': 'application/json',
            }
        }).then(response => {
            console.log(response.data[0].startRent);
            console.log(response.data[0].endRent);
            console.log(response.data[0].roomDTO.price);
            console.log(response.data[0].roomDTO.shortDescription);
            console.log(response.data[0].roomDTO.rate); //could be null - if so (gray stars)


            setAmountBookedRooms(response.data);

            setStartRent(response.data[0].startRent);
            setEndRent(response.data[0].endRent);
            setPrice(response.data[0].roomDTO.price);
            setShortDescription(response.data[0].roomDTO.shortDescription);
            setRate(response.data[0].roomDTO.rate);

        }).catch(error => {
                console.error(error);
            }
        );
    }, []);


    const [profileFormAbleToChange, setProfileFormAbleToChange] = React.useState(false);


    const validationsSchemaProfileData = yup.object().shape({
        name: yup.string().typeError('Повинен бути текст').required(`Поле обов’язкове`),
        surname: yup.string().typeError('Повинен бути текст').required(`Поле обов’язкове`),

        phone: yup.string().typeError('Повинен бути текст').required(`Поле обов’язкове`),
        mail: yup.string().email('Невірна адреса').required(`Поле обов’язкове`),
        nickname: yup.string().typeError('Невірна адреса').required(`Поле обов’язкове`),
        yearsOld: yup.string().typeError('Невірна адреса').required(`Поле обов’язкове`),
        balance: yup.number().positive('Баланс має бути плюсовим').required('Поле обов’язкове'),
        

        password: yup.string().typeError('Повинен бути текст').required(`Поле обов’язкове`).min(8, "Мінімум 8 символів"),
        confirmPassword: yup.string().min(8, "Мінімум 8 символів").oneOf([yup.ref('password')], 'Паролі не співпадають').required(`Поле обов’язкове`)
    })
    
    
    const profileDataFunc = (values) => {

        setProfileFormAbleToChange(false);
        alert("Your data: \n" + 
            "Name: " + values.name + "\n" + 
            "Surname: " + values.surname + "\n" + 
            "Nickname: " + values.nickname + "\n" +
            "Mail: " + values.mail + "\n" +
            "YearsOld: " + values.yearsOld + "\n" +
            "Phone: " + values.phone +"\n" +
            "Balance: " + values.balance +"\n" +
            "Password: " + values.password + "\n" +
            "Confirm Password: " + values.confirmPassword
        );


        axios.put('http://localhost:8080/hotel-rent/users', {
                name: values.name,
                surname: values.surname,
                nickname: values.nickname,
                mail: values.mail,
                yearsOld: values.yearsOld.split(' ')[0],
                phone: values.phone,
                balance: values.balance, //new value for balance
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



  return (
    <div>

       <Header />





        <div className={s.mainWrapper}>
            <div className={s.sidebar}>
                <NavLink to="/profile" activeClassName={s.activeLink} >Бронювання</NavLink>
                <NavLink to="/profile/personal-data" activeClassName={s.activeLink} >Особиста інформація</NavLink>
            </div>

            {props.tab === 'booked-rooms' ? 

                <div className={s.bookedRoomWrapper}>  
                    {amountBookedRooms.map( el => (


                        <div className={s.bookedRoom}>
                            <img className={s.photoRoomBooked} src={images[el-1]} alt="" width='60%'/>
                            <div className={s.bookedRoomPhotoRates}>
                                <img className={s.iconStars} src={
                                    (() => {
                                        switch (rate[el-1]) {
                                        case 1:   return star1;
                                        case 2:   return star2;
                                        case 3:   return star3;
                                        case 4:   return star4;
                                        case 5:   return star5;
                                        default:      return star5;
                                        }
                                    })()
                                
                                } alt=""  />
                                
                                <span className={s.descriptionOfRoom} >{shortDescription[el-1]}</span>
                                <span className={s.priceOfRoom}><i>Ціна: {price[el-1]} грн ({amountOfPerson[el-1]}-х місний)</i></span>
                                <span className={s.dateOfVisiting}>Заїд: {startRent}, виїзд: {endRent}</span>

                                <div className={s.buttonToReserved} >Заброньовано</div>
                                <div className={s.buttonToCancel} >Відмінити бронь</div>
                            </div>
                        </div> 
                    
            
                    ))}
                 </div>
                
                : 

                <Formik
                        initialValues={{
                            name: name,
                            surname: surname,
                            nickname: nickname,
                            mail: mail,
                            yearsOld: yearsOld,
                            phone: phone,
                            balance: balance,
                            password: password,
                            confirmPassword: password,                
                        }}
                        validateOnBlur
                        onSubmit={(values) => profileDataFunc(values)}
                        validationSchema={validationsSchemaProfileData}
                    >
                    {({ values, errors, touched, handleChange, handleBlur, setFieldValue, handleSubmit, isValid, dirty }) => (
                        
                        <div className={s.formOfPerfonalData}>
                            {profileFormAbleToChange ?
                                <input 
                                    type={`text`} 
                                    name={`name`}
                                    onChange={handleChange}
                                    onBlur={handleBlur}
                                    value={values.name}  
                                                
                                    placeholder="Ваше ім’я" 
                                /> 
                                : 
                                <input 
                                    type={`text`} 
                                    name={`name`}
                                    onChange={handleChange}
                                    onBlur={handleBlur}
                                    value={values.name}  
                                                
                                    placeholder="Ваше ім’я" 
                                    disabled
                                />
                            }
                            { touched.name && errors.name && <span className={s.errorMessageProfileData}>{errors.name}</span> }



                            {profileFormAbleToChange ?
                                <input 
                                    type={`text`} 
                                    name={`surname`}
                                    onChange={handleChange}
                                    onBlur={handleBlur}
                                    value={values.surname}  
                                                
                                    placeholder="Ваше прізвище"
                                />
                                : 
                                <input 
                                    type={`text`} 
                                    name={`surname`}
                                    onChange={handleChange}
                                    onBlur={handleBlur}
                                    value={values.surname}  
                                                
                                    placeholder="Ваше прізвище"
                                    disabled 
                                />
                            }
                            { touched.surname && errors.surname && <span className={s.errorMessageProfileData}>{errors.surname}</span> }



                            {profileFormAbleToChange ?
                                <input 
                                    type={`text`} 
                                    name={`nickname`}
                                    onChange={handleChange}
                                    onBlur={handleBlur}
                                    value={values.nickname}  
                                                
                                    placeholder="Ваш логін" 
                                />
                                : 
                                <input 
                                    type={`text`} 
                                    name={`nickname`}
                                    onChange={handleChange}
                                    onBlur={handleBlur}
                                    value={values.nickname}  
                                                
                                    placeholder="Ваш логін" 
                                    disabled
                                />
                            }
                            { touched.nickname && errors.nickname && <span className={s.errorMessageProfileData}>{errors.nickname}</span> }


                            {profileFormAbleToChange ?
                                <input 
                                    type={`text`} 
                                    name={`mail`}
                                    onChange={handleChange}
                                    onBlur={handleBlur}
                                    value={values.mail}  
                                                
                                    placeholder="Ваша пошта" 
                                />
                                : 
                                <input 
                                    type={`text`} 
                                    name={`mail`}
                                    onChange={handleChange}
                                    onBlur={handleBlur}
                                    value={values.mail}  
                                                
                                    placeholder="Ваша пошта" 
                                    disabled
                                />
                            }
                            { touched.mail && errors.mail && <span className={s.errorMessageProfileData}>{errors.mail}</span> }


                            {profileFormAbleToChange ?
                                <input 
                                    type={`text`} 
                                    name={`yearsOld`}
                                    onChange={handleChange}
                                    onBlur={handleBlur}
                                    value={values.yearsOld}  
                                                
                                    placeholder="Скільки вам років?" 
                                />
                                : 
                                <input 
                                    type={`text`} 
                                    name={`yearsOld`}
                                    onChange={handleChange}
                                    onBlur={handleBlur}
                                    value={values.yearsOld}  
                                                
                                    placeholder="Скільки вам років?" 
                                    disabled
                                />
                            }
                            { touched.yearsOld && errors.yearsOld && <span className={s.errorMessageProfileData}>{errors.yearsOld}</span> }


                            {profileFormAbleToChange ?
                                <input 
                                    type={`text`} 
                                    name={`phone`}
                                    onChange={handleChange}
                                    onBlur={handleBlur}
                                    value={values.phone}  
                                                
                                    placeholder="Ваш номер телефона" 
                                />
                                : 
                                <input 
                                    type={`text`} 
                                    name={`phone`}
                                    onChange={handleChange}
                                    onBlur={handleBlur}
                                    value={values.phone}  
                                                
                                    placeholder="Ваш номер телефона" 
                                    disabled
                                />
                            }
                            { touched.phone && errors.phone && <span className={s.errorMessageProfileData}>{errors.phone}</span> }




                            {profileFormAbleToChange ?
                                <input 
                                    type={`number`}
                                    name={`balance`}
                                    onChange={handleChange}
                                    onBlur={handleBlur}
                                    value={values.balance}  
                                                
                                    placeholder="Ваш баланс" 
                                />
                                : 
                                <input 
                                    type={`number`} 
                                    name={`balance`}
                                    onChange={handleChange}
                                    onBlur={handleBlur}
                                    value={values.balance}  
                                                
                                    placeholder="Ваш баланс" 
                                    disabled
                                />
                            }
                            { touched.balance && errors.balance && <span className={s.errorMessageProfileData}>{errors.balance}</span> }






                            {profileFormAbleToChange ?
                                <input 
                                    type={`password`} 
                                    name={`password`}
                                    onChange={handleChange}
                                    onBlur={handleBlur}
                                    value={values.password}  
                                                
                                    placeholder="Пароль" 
                                />
                                : 
                                <input 
                                    type={`password`} 
                                    name={`password`}
                                    onChange={handleChange}
                                    onBlur={handleBlur}
                                    value={values.password}  
                                                
                                    placeholder="Пароль" 
                                    disabled
                                />
                            }
                            { touched.password && errors.password && <span className={s.errorMessageProfileData}>{errors.password}</span> }


                            {profileFormAbleToChange ?
                                <input 
                                    type={`password`} 
                                    name={`confirmPassword`}
                                    onChange={handleChange}
                                    onBlur={handleBlur}
                                    value={values.confirmPassword}  
                                                
                                    placeholder="Підтвердити пароль" 
                                />
                                : 
                                <input 
                                    type={`password`} 
                                    name={`confirmPassword`}
                                    onChange={handleChange}
                                    onBlur={handleBlur}
                                    value={values.confirmPassword}  
                                                
                                    placeholder="Підтвердити пароль" 
                                    disabled
                                />
                            }
                            { touched.confirmPassword && errors.confirmPassword && <span className={s.errorMessageProfileData}>{errors.confirmPassword}</span> }

                            {profileFormAbleToChange ? 
                                <div className={s.buttonToChangeData} onClick={handleSubmit}>Зберегти зміни</div>
                                : 
                                <div className={s.buttonToChangeData} onClick={() => setProfileFormAbleToChange(true)} >Змінити особисті дані</div>
                            }
                        </div> 
                        
                    )}
                </Formik>
                
            }

            
        </div>




        <Footer />
        
                

    </div>
  )
}


export default ProfileUser;