import React, {useState} from 'react';
import NewListButton from "./buttons/NewListButton";
import InputForm from "./InputForm";

const CreateAList = (props) => {
    const [isButton, setIsButton] = useState(true);

    function listButtonHandleClick() {
        setIsButton(false);
    }

    function submitFormHandleClick(title) {
        props.saveNewList(title);
        setIsButton(true);
    }

    if (isButton) return (
        <NewListButton onClick={listButtonHandleClick}/>
    ); else
        return (
            <div className='List'>
                <InputForm id='new_list_form'
                           placeholder='назови список'
                           doSubmit={submitFormHandleClick}/>
                <button className='ButtonWithText'
                        onClick={() => setIsButton(true)}>cancel
                </button>
            </div>

        );
};

export default CreateAList;