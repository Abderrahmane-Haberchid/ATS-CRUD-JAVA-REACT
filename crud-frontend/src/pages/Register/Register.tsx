import { Link, useNavigate } from 'react-router-dom';
import './Register.css';
import axios from 'axios';
import { useState } from 'react';
import type { IUser } from '../../interfaces/IUser';

function Register() {

    const [name, setName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const navigate = useNavigate();

    const handleSubmit = () => {

        const user: IUser = {
            name,
            email,
            password
        };

        axios.post('http://localhost:8083/api/v1/auth/signUp', user)
                .then(response => {
                    if (response.status === 201) {
                        localStorage.setItem('token', response.data.jwt); 
                        console.log('User registered successfully:', response.data);
                        navigate('/home');
                    }
                })
                .catch(error => {
                    console.error('There was an error registering the user:', error);
                });
    }

  return (
    <div>
        <h1>Register</h1>
        <form style={{padding: '20px', width: '60%'}}>

            <div style={{marginBottom: '20px', fontSize: '18px'}}>
                <label htmlFor="username">Username:</label>
                <input type="text" required onChange={(e) => setName(e.target.value)} />
            </div>

            <div style={{marginBottom: '20px', fontSize: '18px'}}>
                <label htmlFor="mail">Email:</label>
                <input type="mail" onChange={(e) => setEmail(e.target.value)} required />
            </div>

            <div style={{marginBottom: '20px', fontSize: '18px'}}>
                <label htmlFor="password">Password:</label>
                <input type="password" onChange={(e) => setPassword(e.target.value)} required />
            </div>

            <button type="submit" onClick={handleSubmit}>Register</button>

            <Link to="/register" style={{marginLeft: '20px', fontSize: '18px'}}>
                Register
            </Link>
        </form>
    </div>
  )
}

export default Register