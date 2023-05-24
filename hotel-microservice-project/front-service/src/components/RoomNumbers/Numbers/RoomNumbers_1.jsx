import React, { useEffect } from 'react';
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
import { Link, useLocation } from 'react-router-dom';
import { Formik } from 'formik';
import * as yup from 'yup';





const RoomNumbers_1 = (props) => {

  //After redirect open top part of page  
  useEffect(() => {
    window.scrollTo(0, 0);
  }, []);



  const validationsSchemaComment = yup.object().shape({

    commentInput: yup.string().typeError('Повинен бути текст').required(`Всі поля обов’язкові!`)
    
  })


  const commentFunc = (values) => {
    alert("Data: \n" + 
    "Comment text: " + values.commentInput);

    console.log(values);

    // history.push('/cabinet')
  }


  return (
    <div>

      <Header id="top"/>







        <div className={s.aboutRoomWrapper}>
            <div className={s.aboutRoom}>
                <img className={s.photoRoomAbout} src={photoRoom_1} alt="" width='55%'/>
                <div className={s.aboutRoomPhotoRates}>
                    <img className={s.iconStars} src={iconStars_5} alt=""  />
                    <span className={s.descriptionOfRoom} >Номер з ліжком розміру king-size і балконом</span>
                    <span className={s.priceOfRoom}><i>Ціна: 3 300 грн</i></span>

                    <div className={s.buttonToReserve} >Забронювати</div>
                </div>
            </div>


            <div className={s.aboutRoomDescription}>
                <h3>Опис</h3>
                <p>Сучасний однокімнатний номер виконаний в стилі Лофт, підійде для одно, 
                    або двомісного розміщення. Тут буде комфортно як подружжю з дитиною, так і 
                    гостям котрі відвідують місто в справах. Площа номерів цієї категорії до 15 кв.м. 
                    Зручні ліжка з ортопедичними матрацами дозволять Вам комфортно виспатися. 
                    А як то кажуть: хороший сон - запорука успіху. В кожному номері присутня система 
                    кондиціонування та опалення. Також до Ваших послуг сейф, супутникове ТБ, 
                    безкоштовний інтернет, телефон, холодильник, фен, капці, набір рушників, 
                    кавоварка, набір посуду.</p>

                
                
                


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
                        <h5>Дмитро Копійченко</h5>
                        <span>Чудовий номер та гарний персонал!</span>
                    </div>
                    <div className={s.comment}>
                        <h5>Олег Гавриленко</h5>
                        <span>Рекомендую!</span>
                    </div>
                </div>
            </div>
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

                    <Link to="/rooms-numbers/1" preventScrollReset={true} activeClassName={s.activeLink} ><div className={s.buttonToReserve}>Забронювати</div></Link>
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

                    <Link to="/rooms-numbers/5" preventScrollReset={true} activeClassName={s.activeLink} ><div className={s.buttonToReserve} >Забронювати</div></Link>
                </div>
            </div>


            <div className={s.buttonAddNewRoom}>Додати номер</div>
        </div>






    

        <Footer />
        

    </div>
  )
}


export default RoomNumbers_1;