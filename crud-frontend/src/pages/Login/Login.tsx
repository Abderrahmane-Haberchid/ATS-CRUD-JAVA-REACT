import { Link, useNavigate } from 'react-router-dom';
import './Login.css';
import type { ICredentials } from '../../interfaces/ICredentials';
import axios from 'axios';
import { useState } from 'react';

function Login() {

    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();

    const handleSubmit = () => {
        const credentials: ICredentials = {
            email,
            password
        };

        axios.post('http://localhost:8083/api/v1/auth/signIn', credentials)
                .then((response) => {
                    if (response.status === 200) {
                        localStorage.setItem('token', response.data.jwt); 
                        console.log('Login successful:', response.data.userDetails);
                        navigate('/home'); 
                    }
                })
                .catch((error) => {
                    console.error('There was an error logging in:', error);
                    alert('Login failed. Please check your credentials.');
                }
        );
    } 

  return (
    <div className='wrapper'>
        <h1>Login</h1>
        <form style={{padding: '20px', width: '60%'}}>

            <div style={{marginBottom: '20px', fontSize: '18px'}}>
                <label htmlFor="username">Username:</label>
                <input type="text" onChange={(e) => setEmail(e.target.value)} required />
            </div>

            <div style={{marginBottom: '20px', fontSize: '18px'}}>
                <label htmlFor="password">Password:</label>
                <input type="password" onChange={(e) => setPassword(e.target.value)} required />
            </div>

            <button type="submit" onClick={handleSubmit}>Login</button>

            <Link to="/register" style={{marginLeft: '20px', fontSize: '18px'}}>
                Register
            </Link>
        </form>
    </div>
  );
}

export default Login;