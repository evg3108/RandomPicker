import React, {useState} from 'react';
import ListOfEntries from "./ListOfEntries";
import {v4 as uuidv4} from "uuid";
import CreateAList from "./CreateAList";

const AppField = () => {
    const [lists, setLists] = useState([
        {id: 1, title: 'Things to do'},
        {id: 2, title: 'Games to play'},
    ])

    function saveNewList(title) {
        const newList = {id: uuidv4(), title: title}
        setLists(lists.concat(newList))
    }

    function editList(id) {
        /*TODO*/
    }

    function deleteList(id) {
        setLists(lists.filter(list => list.id !== id))
    }

    return (
        <div className='App'>
            {lists.map(list =>
                <ListOfEntries deleteList={deleteList}
                               editList={editList}
                               title={list.title}
                               id={list.id}
                               key={list.id}/>
            )}

            <CreateAList className='CreateAList' saveNewList={saveNewList}/>
        </div>

    );
};

export default AppField;