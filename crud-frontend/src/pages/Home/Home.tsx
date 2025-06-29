import React, { useEffect } from 'react'
import { useNavigate } from 'react-router-dom'
import './Home.css'

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
        
        <div className='left-wrapper'>

            <div className='left-wrapper-header'>
                <div>
                    <h1>Welcome to the Home Page</h1>
                </div>
                <div>
                    <p>Products(10)</p>
                </div>
                <div>
                    <button className='add-product-button' onClick={() => navigate('/add-product')}>Add Product</button>
                </div>
            </div>

            <div className='left-wrapper-content'>
                        
            </div>
        </div>

        <div className='right-wrapper'>
            <h2>Log out</h2>
        </div>
    </div>
  )
}

export default Home