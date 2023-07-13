import React from 'react';
import './home.css';
import { HiLocationMarker } from 'react-icons/hi';
import CountUp from 'react-countup';
import { Footer } from './Footer';

export const Home = () => {
  return (
    <div>
    <section className="hero-wrapper">
      <div className="paddings innerWidth">
        <div className="flexColStart hero-left">
          <div className="hero-title">
            {/* <div className="orange-circle"></div> */}
            <h1>Fast,<br/>Free way<br/>to get Experts</h1>
          </div>
          <div className="flexColStart hero-des">
            <span>Experience the difference of professional care for your appliances.</span>
            <span> Trust our experts to keep things running smoothly.</span>
          </div>
          <div className="flexCenter search-bar">
            <HiLocationMarker color="black" size={30} />
            <input type="text" />
            <button className="button">Search</button>
          </div>
          <div className="flexCenter stats">
            <div className="flexColStart stat">
              <span>
                <CountUp start={8800} end={9000} duration={4} />
                <span> +</span>
              </span>
                <span>Happy Users</span>
            </div>
            <div className="flexColStart stat">
              <span>
                <CountUp start={170} end={200} duration={4} />
                <span> +</span>
              </span>
                <span>Verified Experts</span>
            </div>
            <div className="flexColStart stat">
              <span>
                <CountUp end={20} />
                <span> +</span>
              </span>
                <span>Services</span>
            </div>
          </div>
        </div>

        <div className="flexCenter hero-right">
          <div className="image-container">
            <img src="https://www.werepair.ae/images/Home-Appliances-Image-2.png" alt="work" />
          </div>
        </div>

      </div>
    </section>
    <Footer/>
    </div>
  );
};
