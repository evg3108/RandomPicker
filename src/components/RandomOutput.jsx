import React from 'react';

const RandomOutput = (props) => {
    return (
        <div className='RandomOutputField'>
            <span>{props.randomEntry}</span>
            <button className='ButtonWithText' type='button' onClick={() => props.pickRandomEntry()}>MIX</button>
        </div>
    );
};

export default RandomOutput;