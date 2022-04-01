import React from 'react';

const EditButton = (props) => {
    return (
        <button type='button'
                className='EditButton'
                onClick={() => props.edit()}/>
    );
};

export default EditButton;