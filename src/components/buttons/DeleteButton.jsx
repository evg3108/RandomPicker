import React from 'react';

const DeleteButton = (props) => {
    return (
        <button type='button'
                className='DeleteButton'
                onClick={() => props.delete()}/>
    );
};

export default DeleteButton;