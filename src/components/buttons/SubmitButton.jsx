import React from 'react';

const SubmitButton = (props) => {
    return (
        <button type='button'
                className='ButtonWithText'
                value='submitButton'
                onClick={() => props.onClick()}
        >confirm</button>
    );
};

export default SubmitButton;