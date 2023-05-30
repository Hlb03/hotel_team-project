import React, { useEffect } from 'react';
import s from '../RoomNumbers.module.css';


import photoRoom_3 from '../../../assets/images/photoRoom_3.png';
import iconStars_5 from '../../../assets/images/iconStars_5.png';



import Footer from '../../Footer/Footer';
import Header from '../../Header/Header';



import { Formik } from 'formik';
import * as yup from 'yup';
import axios from 'axios';





const RoomNumbers_3 = (props) => {

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
                <img className={s.photoRoomAbout} src={photoRoom_3} alt="" width='55%'/>
                <div className={s.aboutRoomPhotoRates}>
                    <img className={s.iconStars} src={iconStars_5} alt=""  />
                    <span className={s.descriptionOfRoom} >Люкс з гідромасажною ванною</span>
                    <span className={s.priceOfRoom}><i>Ціна: 1 500 грн</i></span>

                    <div className={s.buttonToReserve} >Забронювати</div>
                </div>
            </div>


            <div className={s.aboutRoomDescription}>
                <h3>Опис</h3>
                <p>В двокімнатному номері "Люкс", гостям надається двоспальне ліжко з ортопедичним 
                    матрацом, свіжа біла постільна білизна відмінної якості, а також білий махровий 
                    халат. Зручні подушки і ковдри фірми "Billerbeck", забезпечать вам якісний 
                    відпочинок. Великий телевізійний екран дозволить гостям дізнаватися останні 
                    новини, а наявність міні-холодильника, кондиціонера, душової кабіни з рушниками, 
                    шампунем, милом зроблять перебування в номері максимально комфортним.</p>

                
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


export default RoomNumbers_3;