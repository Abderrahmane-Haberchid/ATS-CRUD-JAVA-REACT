import React, { useEffect } from 'react'
import { useNavigate } from 'react-router-dom'
import './Home.css'
import Header from '../../components/Header';

function Home() {

    const navigate = useNavigate();

    useEffect(() => {
        const token = localStorage.getItem('token');
        if (!token) {
            navigate('/login'); 
        }
    }, []);

  return (
    <div className='wrapper'>

            <div className='left-wrapper-header'>
                <Header />
            </div>

    </div>
  )
}

export default Home