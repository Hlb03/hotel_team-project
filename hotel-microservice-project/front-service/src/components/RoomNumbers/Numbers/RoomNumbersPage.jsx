import React, { useEffect, useState } from 'react';
import s from '../RoomNumbers.module.css';




import photoRoom_1 from '../../../assets/images/photoRoom_1.png';
import photoRoom_2 from '../../../assets/images/photoRoom_2.png';
import photoRoom_3 from '../../../assets/images/photoRoom_3.png';
import photoRoom_4 from '../../../assets/images/photoRoom_4.png';
import photoRoom_5 from '../../../assets/images/photoRoom_5.png';
import photoRoom_6 from '../../../assets/images/photoRoom_6.png';
import iconStars_5 from '../../../assets/images/iconStars_5.png';

import star1 from '../../../assets/images/star1.png';
import star2 from '../../../assets/images/star2.png';
import star3 from '../../../assets/images/star3.png';
import star4 from '../../../assets/images/star4.png';
import star5 from '../../../assets/images/star5.png';

import { useNavigate } from "react-router-dom";



import Header from '../../Header/Header';
import Footer from '../../Footer/Footer';


import { Formik } from 'formik';

import * as yup from 'yup';
import axios from 'axios';
import { useLocation } from 'react-router-dom';
import { unstable_HistoryRouter } from 'react-router-dom';








const RoomNumbersPage = (props) => {

    const navigate = useNavigate();

    const { state } = useLocation();

    const [id, setId] = useState(1);
    const [images, setImages] = useState([photoRoom_1, photoRoom_2, photoRoom_3, photoRoom_4, photoRoom_5, photoRoom_6]);
    const [price, setPrice] = useState(3300);
    const [shortDescription, setShortDescription] = useState("Номер з ліжком розміру king-size і балконом");
    const [longDescription, setLongDescription] = useState("Номер з ліжком розміру king-size і балконом, Номер з ліжком розміру king-size і балконом,Номер з ліжком розміру king-size і балконом,Номер з ліжком розміру king-size і балконом");
    const [amountOfPerson, setAmountOfPerson] = useState(2);
    const [rate, setRate] = useState(4);



    const [comments, setComments] = useState([
        {
            nickname: "Anton",
            dateTimeResponse: "22.05.2023",
            comment: "Great Room!",
            rate: 3
        },
        {
            nickname: "Valik",
            dateTimeResponse: "24.05.2023",
            comment: "Brilliant!",
            rate: 3
        },
        {
            nickname: "Artem",
            dateTimeResponse: "29.05.2023",
            comment: "Like!",
            rate: 1
        }
    ]);


  //After redirect open top part of page  
  useEffect(() => {
    window.scrollTo(0, 0);


    // if(!state.number){
    //     navigate("/");
    // }

    //TODO: CHANGE URL (IT HAS A TO TAKE INFO ABOUT ROOM ID FROM USER WHEN HE/SHE CLICKS ON CERTAIN ROOM)
    axios.get('http://localhost:8080/hotel-rent/rooms/info/'+state.number, {
        headers: {
            'Content-Type': 'application/json',
        }
        }).then(response => {
            console.log(response.data.id); // add it to post params to connect comment and room
            console.log(response.data.price);
            console.log(response.data.shortDescription);
            console.log(response.data.longDescription);
            console.log(response.data.rate);
            console.log(response.data.amountOfPerson);

            console.log(`${response.data.comment[0].nickname}   (${response.data.comment[0].dateTimeResponse})`) // first row of comment (user_nickname (*date_of_comment))
            console.log(`${response.data.comment[0].comment}     ${response.data.comment[0].rate}`) // this is a comment itself + rate for comment
        
        

            setId(response.data.id);
            setPrice(response.data.price);
            setShortDescription(response.data.shortDescription);
            setLongDescription(response.data.longDescription);
            setAmountOfPerson(response.data.amountOfPerson);
            setRate(response.data.rate);


            setComments(response.data.comment);


            // setAmountComments(response.data.comment);
            // setCommentNickname(response.data.comment[0].nickname);
            // setCommentDateTimeResponse(response.data.comment[0].dateTimeResponse);
            // setCommentText(response.data.comment[0].comment);
            // setCommentRate(response.data.comment[0].rate);

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



    // TODO: ADD OVER HERE RATE VALUE (STARS) WITH NAME `rate` + Room_id, WHICH YOU SHOULD TAKE FROM GET REQUEST, name this id `room`
    axios.post('http://localhost:8080/hotel-rent/response', {
            id: id,
            comment: values.commentInput,
            rate: rate,
            room: state.number
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

      <Header id="top"/>



        <div className={s.aboutRoomWrapper}>

            <div className={s.aboutRoom}>
                <img className={s.photoRoomAbout} src={images[state.number-1]} alt="" width='55%'/>
                <div className={s.aboutRoomPhotoRates}>
                    <img className={s.iconStars} src={
                        (() => {
                            switch (rate[state.number]) {
                              case 1:   return star1;
                              case 2:   return star2;
                              case 3:   return star3;
                              case 4:   return star4;
                              case 5:   return star5;
                              default:      return star5;
                            }
                        })()
                    } alt=""  />

                    <span className={s.descriptionOfRoom} >{shortDescription}</span>
                    <span className={s.priceOfRoom}><i>Ціна: {price} грн ({amountOfPerson}-х місний)</i></span>

                    <div className={s.buttonToReserve} >Забронювати</div>
                </div>
            </div>


            <div className={s.aboutRoomDescription}>
                <h3>Опис</h3>
                <p> {longDescription}</p>

                
                
                


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

                    {comments.map( comment => (
                        <div className={s.comment}>
                            <h5> 
                                {comment.nickname}
                                <span><i>( {comment.dateTimeResponse} )</i></span> 
                                <img className={s.iconStars} src={
                                    (() => {
                                        switch (comment.rate) {
                                        case 1:   return star1;
                                        case 2:   return star2;
                                        case 3:   return star3;
                                        case 4:   return star4;
                                        case 5:   return star5;
                                        default:      return star5;
                                        }
                                    })()
                                
                                } alt="" />
                            </h5>

                            <span>{comment.comment}</span>
                        </div>
                    ))}

                </div>
            </div>
        </div>


        <Footer />        

    </div>
  )
}


export default RoomNumbersPage;