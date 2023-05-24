import React from 'react';
import s from './Footer.module.css';


import footer_image from '../../assets/images/footer_image.png';

import facebook from '../../assets/images/facebook.png';
import instagram from '../../assets/images/instagram.png';
import twitter from '../../assets/images/twitter.png';






const Footer = (props) => {
  return (
    
        <footer>
            <img className={s.footerImage} src={footer_image} alt="" />

            <div className={s.footerBlock}>
                <h2>Маєте питання?</h2>
                <h4>Зателефонуйте, або напишіть</h4>

                <div className={s.socialInformation}>
                    <div className={s.infoBlock} >
                        <span>Phone</span>
                        <span>123-456-7890</span>
                    </div>

                    <div className={s.infoBlock} >
                        <span>Email</span>
                        <span>hello@reallygreatsite.com</span>
                    </div>

                    <div className={s.socialBlock} >
                        <span>SOCIAL</span>
                        <div className={s.socialLinks} >
                            <img className={s.iconFacebook} src={facebook} alt="" />
                            <img className={s.iconInstagram} src={instagram} alt="" />
                            <img className={s.iconTwitter} src={twitter} alt="" />
                        </div>
                    </div>
                </div>
            </div>
        </footer>
  )
}


export default Footer;