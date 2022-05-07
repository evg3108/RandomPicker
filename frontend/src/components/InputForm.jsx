import React, {useState} from 'react';

const InputForm = (props) => {
    const [input, setInput] = useState('')

    function doSubmit(input) {
        props.doSubmit(input)
        setInput('')
    }

    return (
        <div className='SubmitForm'>
            <input type='text'
                   className='TextInput'
                   value={input}
                   placeholder={props.placeholder}
                   onChange={event => setInput(event.target.value)}/>
            <button type='button'
                    className='ButtonWithText'
                    value='submitButton'
                    onClick={() => doSubmit(input)}
            >confirm
            </button>
        </div>
    );
};

export default InputForm;