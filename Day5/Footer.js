import "./footer.css"
import React from 'react'
import {FaFacebook, FaHome, FaInstagram, FaLinkedin, FaMailBulk, FaPhone, FaTwitter} from "react-icons/fa";

export const Footer = () => {
  return (
    <div className="footer">
        <div className="footer-container">
           <div className="left">
              <div className="location">
                <FaHome size={20} style={{color:"fff",marginRight:"2rem"}}/>
                <div>
                    <p>C102, Second Floor</p>
                    <p>Chennai-600028</p>
                </div>
              </div>
              <div className="phone">
                <p><FaPhone size={20} style={{color:"fff",marginRight:"2rem"}}/>
                 +91 6798445456</p>
              </div>
              <div className="email">
                <p><FaMailBulk size={20} style={{color:"fff",marginRight:"2rem"}}/>
                 appliance@gmail.com</p>
              </div>
           </div>
           <div className="right">
               <h4>About the Company</h4>
               <p>Our mission is to make a positive impact by delivering reliable and innovative services that exceed expectations.</p>
               <div className="social">
                <a href="https://www.facebook.com/" target="_blank" rel="noreferrer">
                <FaFacebook size={30} style={{color:"fff",marginRight:"1rem"}}/>
                </a>
                <a href="https://twitter.com/" target="_blank" rel="noreferrer">
                <FaTwitter size={30} style={{color:"fff",marginRight:"1rem"}}/> 
                </a>
                <a href="https://in.linkedin.com/" target="_blank" rel="noreferrer">
                <FaLinkedin size={30} style={{color:"fff",marginRight:"1rem"}}/>
                </a>
                <a href="https://www.instagram.com/" target="_blank" rel="noreferrer">
                <FaInstagram size={30} style={{color:"fff",marginRight:"1rem"}}/>
                </a>
               </div>
           </div>
           <div className="moblie-apps">
               <h4>Download our app</h4>
               <p className="apps-message">Tackle your to-do list wherever you are with our mobile app.</p>
               <a className="app-logo" data-behavior="app-link-clicked" href="https://apps.apple.com" >
               <img alt="Download app from Apple Store" height="45" data-src="https://assets.taskrabbit.com/v3/assets/web/en-US/appstore_badge-86c9954e1457d27db013c1f10a96ffaba845e5af7765c4ef9df4ac1549e47d67.svg" src="https://assets.taskrabbit.com/v3/assets/web/en-US/appstore_badge-86c9954e1457d27db013c1f10a96ffaba845e5af7765c4ef9df4ac1549e47d67.svg"/>
               </a>
               <a className="app-logo" data-behavior="app-link-clicked" href="https://play.google.com/store">
              <img alt="Download app from Google Play Store" height="45" data-src="https://assets.taskrabbit.com/v3/assets/web/en-US/google_play_badge-49e6ea4ba78ca19b1246873b3369891cb6e289515c11418f1fce4cb3a694c18a.svg" src="https://assets.taskrabbit.com/v3/assets/web/en-US/google_play_badge-49e6ea4ba78ca19b1246873b3369891cb6e289515c11418f1fce4cb3a694c18a.svg"/>
               </a>
               
           </div>
        </div>
        <footer style={{backgroundColor: "#333", color: "#fff", padding: "10px",paddingTop:"30px", textAlign: "center"}}>
        &copy; 2023 Home Appliances Service Portal. All rights reserved.
      </footer> 
    </div>
  )
}