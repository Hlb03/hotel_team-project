import React, { useEffect } from 'react';
import s from './ProfileUser.module.css';



import photoRoom_1 from '../../assets/images/photoRoom_1.png';
import iconStars_5 from '../../assets/images/iconStars_5.png';


import { NavLink, Route, Routes } from 'react-router-dom';
import Header from '../Header/Header';
import Footer from '../Footer/Footer';



import { Formik } from 'formik';
import * as yup from 'yup';

import axios from 'axios';





const ProfileUser = (props) => {

    useEffect(() => {
        window.scrollTo(0, 0);
    }, []);


    const [profileFormAbleToChange, setProfileFormAbleToChange] = React.useState(false);


    const validationsSchemaProfileData = yup.object().shape({
        name: yup.string().typeError('Повинен бути текст').required(`Поле обов’язкове`),
        surname: yup.string().typeError('Повинен бути текст').required(`Поле обов’язкове`),

        phone: yup.string().typeError('Повинен бути текст').required(`Поле обов’язкове`),
        mail: yup.string().email('Невірна адреса').required(`Поле обов’язкове`),
        nickname: yup.string().typeError('Невірна адреса').required(`Поле обов’язкове`),
        yearsOld: yup.string().typeError('Невірна адреса').required(`Поле обов’язкове`),

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
            "Password: " + values.password + "\n" +
            "Confirm Password: " + values.confirmPassword
        );


        //TODO
        axios.post('/profile-edit', { 
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


  return (
    <div>

       <Header />





        <div className={s.mainWrapper}>
            <div className={s.sidebar}>
                <NavLink to="/profile" activeClassName={s.activeLink} >Бронювання</NavLink>
                <NavLink to="/profile/personal-data" activeClassName={s.activeLink} >Особиста інформація</NavLink>
            </div>

            {props.tab === 'booked-rooms' ? 
                <div className={s.bookedRoom}>
                    <img className={s.photoRoomBooked} src={photoRoom_1} alt="" width='60%'/>
                    <div className={s.bookedRoomPhotoRates}>
                        <img className={s.iconStars} src={iconStars_5} alt=""  />
                        <span className={s.descriptionOfRoom} >Номер з ліжком розміру king-size і балконом</span>
                        <span className={s.priceOfRoom}><i>Ціна: 3 300 грн</i></span>

                        <div className={s.buttonToReserved} >Заброньовано</div>
                        <div className={s.buttonToCancel} >Відмінити бронь</div>
                    </div>
                </div> 
                : 

                <Formik
                        initialValues={{
                            name: 'Григорій',
                            surname: 'Онищенко',
                            nickname: 'Gru02',
                            mail: 'hryhoriyonishchenko@gmail.com',
                            yearsOld: '32 роки',
                            phone: '+380985606565',
                            password: 'Gru02111!',
                            confirmPassword: 'Gru02111!',                
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