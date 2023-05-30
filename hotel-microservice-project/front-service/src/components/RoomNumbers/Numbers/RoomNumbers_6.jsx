import React, { useEffect } from 'react';
import s from '../RoomNumbers.module.css';


import photoRoom_6 from '../../../assets/images/photoRoom_6.png';
import iconStars_5 from '../../../assets/images/iconStars_5.png';




import Header from '../../Header/Header';
import Footer from '../../Footer/Footer';




import { Formik } from 'formik';
import * as yup from 'yup';
import axios from 'axios';





const RoomNumbers_6 = (props) => {

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
                <img className={s.photoRoomAbout} src={photoRoom_6} alt="" width='55%'/>
                <div className={s.aboutRoomPhotoRates}>
                    <img className={s.iconStars} src={iconStars_5} alt=""  />
                    <span className={s.descriptionOfRoom} >Номер трьохмісний</span>
                    <span className={s.priceOfRoom}><i>Ціна: 3 600 грн</i></span>

                    <div className={s.buttonToReserve} >Забронювати</div>
                </div>
            </div>


            <div className={s.aboutRoomDescription}>
                <h3>Опис</h3>
                <p>Просторий і світлий номер, стильний та сучасний, площею до 17 кв.м. Панорамні 
                    вікна цього номера, виходять на тихий, милий дворик. В цьому номері, є зручне 
                    двоспальне ліжко. Також в номері передбачена робоча зона з функціональним 
                    робочим столом та телефоном. Простора ванна кімната обладнана усім необхідним 
                    приладдям. Наші ніжні рушники і халати, огорнуть Вас затишком та домашнім теплом. 
                    Wi-Fi, сейф, холодильник, кавоварка. Перебування в готелі «Stan Gret», зробить 
                    Ваш відпочинок незабутнім.</p>

                
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
                        <h5>Олег Гавриленко </h5>
                        <span>Рекомендую!</span>
                    </div>
                </div>
            </div>
        </div>










        <Footer />



    </div>
  )
}


export default RoomNumbers_6;