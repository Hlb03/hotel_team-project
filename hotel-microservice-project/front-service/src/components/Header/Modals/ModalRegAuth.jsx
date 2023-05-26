import React, { useEffect, useState, useRef } from 'react'
import Modal from 'react-modal';
import s from './Modals.module.css';


import close_icon from '../../../assets/images/close.svg';
import city_icon from '../../../assets/images/city_icon.png';



import { Formik } from 'formik';
import * as yup from 'yup';


const ModalRegAuth = ({ type, isOpen, onAfterOpen, onRequestClose, style, contentLabel, funcClick, subtitle, errorText }) => {    

    const [typeOfModal, setTypeOfModal] = useState(type)
    const [redirectModal, setRedirectModal] = useState(type)


    // // Auth Form
    const validationsSchemaAuth = yup.object().shape({
        mail: yup.string().email('Невірна адреса').required(`Поле обов’язкове`),
        password: yup.string().typeError('Повинен бути текст').required(`Поле обов’язкове`).min(8, "Мінімум 8 символів"),
    })

    // Registration Form
    const validationsSchemaReg = yup.object().shape({
        name: yup.string().typeError('Повинен бути текст').required(`Поле обов’язкове`),
        surname: yup.string().typeError('Повинен бути текст').required(`Поле обов’язкове`),

        phone: yup.string().typeError('Повинен бути текст').required(`Поле обов’язкове`),
        mail: yup.string().email('Невірна адреса').required(`Поле обов’язкове`),
        nickname: yup.string().typeError('Невірна адреса').required(`Поле обов’язкове`),
        yearsOld: yup.string().typeError('Невірна адреса').required(`Поле обов’язкове`),

        password: yup.string().typeError('Повинен бути текст').required(`Поле обов’язкове`).min(8, "Мінімум 8 символів"),
        confirmPassword: yup.string().min(8, "Мінімум 8 символів").oneOf([yup.ref('password')], 'Паролі не співпадають').required(`Поле обов’язкове`)
    })



    return (
        <Modal
            isOpen={isOpen}
            onAfterOpen={onAfterOpen}
            style={style}
            contentLabel={contentLabel}
        >

            <div className={s.modal_wrapper}>
                
                <div>{( () => {
                    if(typeOfModal === 'fastReg') {
                        return (
                            <div className={s.headerOfModal}>
                                <img className={s.city_icon} src={city_icon} />
                                <h4>Реєстрація користувача</h4>
                            </div>
                        )
                    } else if(typeOfModal === 'auth') {
                        return (
                            <div className={`${s.headerOfModal} ${s.headerOfModalEnter}`}>
                                <img className={s.city_icon} src={city_icon} />
                                <h4>Вхід в особистий кабінет</h4>
                            </div>
                        )
                    } else {
                        return (
                            null
                        )
                    }
                })() }
                </div>


                <img onClick={() => {
                    onRequestClose()

                    setTypeOfModal(redirectModal)
                }} src={close_icon} className={s.close_icon}></img> 



                {(() => {
                    if (typeOfModal === 'fastReg') {
                        return (
                            <Formik
                            initialValues={{
                                name: '',
                                surname: '',
                                nickname: '',
                                mail: '',
                                yearsOld: '',
                                phone: '',
                                password: '',
                                confirmPassword: '',

                            //     POST
                            //     localhost:8080/hotel-rent/users?name=value.name&
                            }}
                            validateOnBlur
                            onSubmit={(values) => funcClick(values)}
                            validationSchema={validationsSchemaReg}
                            >

                            {({ values, errors, touched, handleChange, handleBlur, handleSubmit, isValid, dirty }) => (

                                <div className={s.modalRegisForm}>

                                <form>
                                    <div className={s.inputFormWrapper}>
                                        <input 
                                            className={touched.name && errors.name ? 'errorInput' : 'name'}
                                            type={`text`} 
                                            name={`name`}
                                            onChange={handleChange}
                                            onBlur={handleBlur}
                                            value={values.name}  
                                            
                                            placeholder="Ваше ім’я" 
                                            />

                                        { touched.name && errors.name && <span className={s.errorMessageReg}>{errors.name}</span> }
                                    </div>
                                    
                                    <div className={s.inputFormWrapper}>
                                        <input 
                                            className={touched.surname && errors.surname ? 'errorInput' : 'surname'}
                                            type={`text`} 
                                            name={`surname`}
                                            onChange={handleChange}
                                            onBlur={handleBlur}
                                            value={values.surname}  
                                            
                                            placeholder="Ваше прізвище" 
                                            />

                                        { touched.surname && errors.surname && <span className={s.errorMessageReg}>{errors.surname}</span> }
                                    </div>





                                    <div className={s.inputFormWrapper}>
                                        <input 
                                            className={touched.nickname && errors.nickname ? 'errorInput' : 'nickname'}
                                            type={`text`} 
                                            name={`nickname`}
                                            onChange={handleChange}
                                            onBlur={handleBlur}
                                            value={values.nickname}  
                                            
                                            placeholder="Ваш логін" 
                                            />

                                        { touched.nickname && errors.nickname && <span className={s.errorMessageReg}>{errors.nickname}</span> }
                                    </div>

                                    


                                    <div className={s.inputFormWrapper}>
                                        <input 
                                            className={touched.mail && errors.mail ? 'errorInput' : 'mail'}
                                            type={`text`} 
                                            name={`mail`}
                                            onChange={handleChange}
                                            onBlur={handleBlur}
                                            value={values.mail}  
                                            
                                            placeholder="Електронна адреса" 
                                            />

                                        { touched.mail && errors.mail && <span className={s.errorMessageReg}>{errors.mail}</span> }
                                    </div>

                                    
                                    <div className={s.inputFormWrapper}>
                                        <input 
                                            className={touched.password && errors.password ? 'errorInput' : 'password'}
                                            type={`password`} 
                                            name={`password`}
                                            onChange={handleChange}
                                            onBlur={handleBlur}
                                            value={values.password}  
                                            
                                            placeholder="Пароль" 
                                            />

                                        { touched.password && errors.password && <span className={s.errorMessageReg}>{errors.password}</span> }
                                    </div>


                                    <div className={s.inputFormWrapper}>
                                        <input 
                                            className={touched.confirmPassword && errors.confirmPassword ? 'errorInput' : 'confirmPassword'}
                                            type={`password`} 
                                            name={`confirmPassword`}
                                            onChange={handleChange}
                                            onBlur={handleBlur}
                                            value={values.confirmPassword}  
                                            
                                            placeholder="Подтвердить пароль" 
                                            />

                                        { touched.confirmPassword && errors.confirmPassword && <span className={s.errorMessageReg}>{errors.confirmPassword}</span> }
                                    </div>









                                    <div className={s.inputFormWrapper}>
                                        <input 
                                            className={touched.yearsOld && errors.yearsOld ? 'errorInput' : 'yearsOld'}
                                            type={`text`} 
                                            name={`yearsOld`}
                                            onChange={handleChange}
                                            onBlur={handleBlur}
                                            value={values.yearsOld}  
                                            
                                            placeholder="Вік" 
                                            />

                                        { touched.yearsOld && errors.yearsOld && <span className={s.errorMessageReg}>{errors.yearsOld}</span> }
                                    </div>





                                    <div className={s.inputFormWrapper}>
                                        <input 
                                            className={touched.phone && errors.phone ? 'errorInput' : 'phone'}
                                            type={`text`} 
                                            name={`phone`}
                                            onChange={handleChange}
                                            onBlur={handleBlur}
                                            value={values.phone}  
                                            
                                            placeholder="Номер телефона" 
                                            />

                                        { touched.phone && errors.phone && <span className={s.errorMessageReg}>{errors.phone}</span> }
                                    </div>


                                </form>


                                    <div className={!isValid || !dirty ? s.regButton_DeActive : s.regButton} onClick={handleSubmit}>
                                           <span className={s.reg_button_text}>Зареєструватись</span>
                                    </div>


                                    <div className={s.login_button_text}>
                                        <span onClick={ () => { 
                                            setRedirectModal(typeOfModal); 
                                            setTypeOfModal('auth'); 
                                        }}>Маєте аккаунт? Увійти.</span>
                                            
                                    </div>

                                </div> 
                            )}

                            </Formik>   
                        )
                    } else if (typeOfModal === 'auth'){
                        return (

                            <Formik
                                initialValues={{
                                    mail: '',
                                    password: '',
                                }}
                                validateOnBlur
                                onSubmit={(values) => funcClick(values)}
                                validationSchema={validationsSchemaAuth}
                            >
                                {({ values, errors, touched, handleChange, handleBlur, handleSubmit, isValid, dirty }) => (
                                    
                                    
                                    // !newPassword && !codeToResetPass && !forgotPass && 
                                    <form className={`${s.modalAuthForm} ${s.open}`} >
                                                                    
                                        <input className={touched.mail && errors.mail ? 'errorInput' : 'mail'}
                                            type={`text`} 
                                            name={`mail`}
                                            onChange={handleChange}
                                            onBlur={handleBlur}
                                            value={values.mail}  
                                            
                                            placeholder="Електронна адреса" 
                                        />

                                        { touched.mail && errors.mail && <span className={s.errorMessageLogin}>{errors.mail}</span> }


                                        <input className={touched.password && errors.password ? 'errorInput' : s.passwordInput}
                                            type={`password`} 
                                            name={`password`}
                                            onChange={handleChange}
                                            onBlur={handleBlur}
                                            value={values.password}  
                                            
                                            placeholder="Пароль" 
                                        />

                                        { touched.password && errors.password && <span className={s.errorMessageLogin}>{errors.password}</span> }


                                        <div className={!isValid || !dirty ? s.loginButton_DeActive : s.loginButton} onClick={handleSubmit}>
                                           <span className={s.login_button_text}>Увійти</span>
                                        </div>


                                        <div className={s.reg_button_text}>
                                            <span onClick={() => { 
                                                setRedirectModal(typeOfModal); 
                                                setTypeOfModal('fastReg'); 
                                            }}>Реєстрація</span>
                                        </div>


                                    </form>
                                )}
                            </Formik>     
                        )
                    } 
                })()}




            </div>
        </Modal>
    )
}

export default ModalRegAuth
