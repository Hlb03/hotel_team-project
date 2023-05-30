import React, { useEffect } from 'react';
import s from '../RoomNumbers.module.css';



import photoRoom_4 from '../../../assets/images/photoRoom_4.png';
import iconStars_5 from '../../../assets/images/iconStars_5.png';


import Header from '../../Header/Header';
import Footer from '../../Footer/Footer';


import { Formik } from 'formik';
import * as yup from 'yup';
import axios from 'axios';





const RoomNumbers_4 = (props) => {

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




      const validationsSchemaComment = yup.object().shape({

        commentInput: yup.string().typeError('Повинен бути текст').required(`Всі поля обов’язкові!`)
        
      })
    
    
      const commentFunc = (values) => {
        alert("Data: \n" + 
        "Comment text: " + values.commentInput);
    
        console.log(values);
    
        // TODO
        axios.post('new-comment', {
            comment: values.commentInput,
        })
        .then(function (response) {
            console.log(response);
        })
        .catch(function (error) {
            console.log(error);
        });
      }




  return (
    <div>

       <Header />







        <div className={s.aboutRoomWrapper}>
            <div className={s.aboutRoom}>
                <img className={s.photoRoomAbout} src={photoRoom_4} alt="" width='55%'/>
                <div className={s.aboutRoomPhotoRates}>
                    <img className={s.iconStars} src={iconStars_5} alt=""  />
                    <span className={s.descriptionOfRoom} >Номер двохмісний</span>
                    <span className={s.priceOfRoom}><i>Ціна: 3 400 грн</i></span>

                    <div className={s.buttonToReserve} >Забронювати</div>
                </div>
            </div>


            <div className={s.aboutRoomDescription}>
                <h3>Опис</h3>
                <p>Номер складається з однієї житлової кімнати та ванної кімнати з душовою кабінкою. 
                    В номері два односпальні ліжка, диван, стіл, стілець, класична шафа, телевізор. 
                    Для вашого комфорту у номері є фен, рушники, індивідуальні засоби гігієни (мило, 
                    шампунь). Наші Сніданки різноманітні та об’ємні. Після поживного сніданку заряду 
                    енергії з ранку вам вистачить на довгі прогулянки містом та вирішення важливих 
                    справ. Сніданки до ваших послуг з 7:00 до 10:00.Безкоштовне Wi-Fi покриття 
                    дозволить завжди залишатись у курсі світових подій та ділитись враженнями від 
                    знайомства зі Львовом у соц мережах.</p>

                
                <Formik
                        initialValues={{
                            commentInput: ''                 
                        }}
                        validateOnBlur
                        onSubmit={(values) => commentFunc(values)}
                        validationSchema={validationsSchemaComment}
                    >
                    {({ values, errors, touched, handleChange, handleBlur, setFieldValue, handleSubmit, isValid, dirty }) => (
                    
                        <div>
                            <div className={s.fieldToAddComment}>
                                <input type="text" placeholder='Додайте коментар' name="commentInput"  onChange={handleChange} />
                                <div className={s.buttonSend} onClick={handleSubmit}>Надіслати</div>
                            </div>

                            { touched.commentInput && errors.commentInput && <span className={s.errorComment}>Введіть коментар!</span> }
                        </div>
                    )}
                </Formik>

                <div className={s.listOfComments}>
                    <div className={s.comment}>
                        <h5>Дмитро Копійченко <span><i>( 31.06.2023 )</i></span></h5>
                        <span>Чудовий номер та гарний персонал!</span>
                    </div>
                    <div className={s.comment}>
                        <h5>Олег Гавриленко <span><i>( 31.06.2023 )</i></span></h5>
                        <span>Рекомендую!</span>
                    </div>
                </div>
            </div>
        </div>






    
        <Footer />
        

    </div>
  )
}


export default RoomNumbers_4;