import React from 'react';
import { Link, NavLink } from 'react-router-dom';
import { useSelector, useDispatch } from 'react-redux';
import { FaBars, FaTimes } from 'react-icons/fa';
import { setLoggedInUser } from '../reduxx/actions';
import './nav.css';

export const Navbar = ({ loggedInUser, onFormSwitch, onLogout }) => {
  const isLoginFormOpen = useSelector((state) => state.isLoginFormOpen);
  const dispatch = useDispatch();

  const showNavBar = () => {
    dispatch(setLoggedInUser());
  };

  return (
    <header>
      <img
        src="https://t4.ftcdn.net/jpg/01/19/95/61/360_F_119956185_Ku8R6D8wt7Y8XdOqf6UnT4r2kffn6OeH.jpg"
        alt="logo"
        className="logo"
      />
      <nav className={isLoginFormOpen ? 'responsive_nav' : ''}>
        <NavLink exact to="/" activeClassName="active" onClick={showNavBar}>
          Home
        </NavLink>
        <NavLink to="/services" activeClassName="active" onClick={showNavBar}>
          Service
        </NavLink>
        <NavLink to="/contact" activeClassName="active" onClick={showNavBar}>
          Contact
        </NavLink>
        {loggedInUser ? (
          <>
            <Link to="/" onClick={onLogout}>
              Logout
            </Link>
            <button className="nav-btn nav-close-btn" onClick={showNavBar}>
              <FaTimes />
            </button>
          </>
        ) : (
          <>
            <NavLink to="/login" activeClassName="active" onClick={showNavBar}>
              Login
            </NavLink>
            <button className="nav-btn nav-close-btn" onClick={showNavBar}>
              <FaTimes />
            </button>
          </>
        )}
      </nav>
      <button className="nav-btn" onClick={showNavBar}>
        <FaBars />
      </button>
    </header>
  );
};
