import React from 'react';
import EditButton from "./buttons/EditButton";
import DeleteButton from "./buttons/DeleteButton";

const Entry = (props) => {
    const id = props.id

    function editEntry() {
        props.edit(id)
    }

    function deleteEntry() {
        props.delete(id)
    }

    return (
        <div className='EntryField'>
            <div>
                <input type='checkbox' name='text_of_task'/>
                <label htmlFor='text_of_task'>{props.description}</label>
            </div>
            <div>
                <EditButton edit={editEntry}/>
                <DeleteButton delete={deleteEntry}/>
            </div>
        </div>
    );
};

export default Entry;