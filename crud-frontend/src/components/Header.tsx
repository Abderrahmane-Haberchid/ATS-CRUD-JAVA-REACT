import React from 'react';
import { useNavigate } from 'react-router-dom';

function Header() {

    const navigate = useNavigate();

  return (
    <div>
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
  )
}

export default Header