import React, { useState } from 'react';
import { useDispatch } from 'react-redux';
import validator from 'validator';
import { setLoggedInUser } from '../reduxx/actions';
import { Link } from 'react-router-dom';

export const Login = ({ onFormSwitch }) => {
  const dispatch = useDispatch();
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [errors, setErrors] = useState({});

  const validateForm = () => {
    let formErrors = {};

    if (!email) {
      formErrors.email = '*Email is required';
    } else if (!validator.isEmail(email)) {
      formErrors.email = '*Invalid email format';
    }

    if (!password) {
      formErrors.password = '*Password is required';
    }

    setErrors(formErrors);

    return Object.keys(formErrors).length === 0;
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (validateForm()) {
      console.log(email);
      dispatch(setLoggedInUser(email));
    }
  };

  return (
    <div className="uth-form">
      <h2>Login</h2>
      <form className="login-form" onSubmit={handleSubmit}>
        <label htmlFor="email">Email</label>
        <input
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          type="email"
          placeholder="Enter email id"
          id="email"
          name="email"
        />
        {errors.email && <div className="error">{errors.email}</div>}
        <label htmlFor="password">Password</label>
        <input
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          type="password"
          placeholder="Enter password"
          id="password"
          name="password"
        />
        {errors.password && <div className="error">{errors.password}</div>}
        <button type="submit">Log In</button>
      </form>
      <Link className="link-btn" to="/register">
        Don't have an account? Register
      </Link>
    </div>
  );
};
