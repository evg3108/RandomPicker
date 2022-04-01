import React from 'react';

const NewListButton = (props) => {
    return (
        <button className='PlusButton' onClick={() => props.onClick()}/>
    );
};

export default NewListButton;