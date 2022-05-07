import React, {useState} from 'react';
import Entry from "./Entry";
import {v4 as uuidv4} from 'uuid';
import InputForm from "./InputForm";
import RandomOutput from "./RandomOutput";
import EditButton from "./buttons/EditButton";
import DeleteButton from "./buttons/DeleteButton";


const ListOfEntries = (props) => {

    const id = props.id

    const [entries, setEntries] = useState([
        {id: 1, description: 'Entry 1'},
        {id: 2, description: 'Entry 2'},
        {id: 3, description: 'Entry 3'}
    ])

    const [randomEntry, setRandomEntry] = useState('')

    function editList() {
        props.editList(id)
    }

    function deleteList() {
        props.deleteList(id)
    }

    function submitEntry(text) {
        const newEntry = {
            id: uuidv4(),
            description: text
        }
        setEntries(entries.concat(newEntry));
    }

    function editEntry(id) {
        const editedList = entries.filter(entry => entry.id !== id)
        setEntries(editedList.concat({id: id, description: 'newValue'}))

    }

    function deleteEntry(idToDelete) {
        setEntries(entries.filter(entry => entry.id !== idToDelete));
    }

    function pickRandomEntry() {
        setRandomEntry((entries[Math.floor(Math.random() * entries.length)]).description);
    }

    return (
        <div className='ListBox'>
            <div className='List'>
                <div className='Title'>
                    <h2>{props.title}</h2>
                    <div>
                        <EditButton edit={editList}/>
                        <DeleteButton delete={deleteList}/>
                    </div>
                </div>
                <div>
                    {entries.map(entry =>
                        <Entry id={entry.id}
                               description={entry.description}
                               key={entry.id}
                               edit={editEntry}
                               delete={deleteEntry}/>
                    )}
                </div>

                <InputForm id='new_entry_form' placeholder='добавь свой вариант' doSubmit={submitEntry}/>
            </div>


            <RandomOutput randomEntry={randomEntry} pickRandomEntry={pickRandomEntry}/>

        </div>

    );
};

export default ListOfEntries;