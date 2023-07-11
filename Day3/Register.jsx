import React, { useState } from 'react';
import validator from 'validator';
import { useDispatch } from 'react-redux';
import { setLoggedInUser } from '../reduxx/actions';
import { Link } from 'react-router-dom';

export const Register = ({ onFormSwitch }) => {
  const dispatch = useDispatch();
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [name, setName] = useState('');
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
    } else if (password.length < 8) {
      formErrors.password = '*Password should be at least 8 characters long';
    } else if (
      !validator.matches(
        password,
        /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]+$/,
      )
    ) {
      formErrors.password =
        '*Password should contain at least one uppercase letter, one lowercase letter, one number, and one special character';
    }

    if (!name) {
      formErrors.name = '*Name is required';
    } else if (!validator.matches(name, /^[a-zA-Z\s]+$/)) {
      formErrors.name = '*Name can only contain letters and spaces';
    } else if (name.length < 3 || name.length > 50) {
      formErrors.name = '*Name should be between 3 and 50 characters';
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
      <h2>Create Account</h2>
      <form className="register-form" onSubmit={handleSubmit}>
        <label htmlFor="name">Full Name</label>
        <input
          value={name}
          onChange={(e) => setName(e.target.value)}
          type="text"
          placeholder="Enter full name"
          id="name"
          name="name"
        />
        {errors.name && <div className="error">{errors.name}</div>}
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
        <button type="submit">Create</button>
      </form>
      <Link className="link-btn" to="/login">
        Have an account? Login
      </Link>
    </div>
  );
};
