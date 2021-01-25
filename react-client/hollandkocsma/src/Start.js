import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';

function Start({ dataArray }) {
  const [text, setText] = useState('');

  async function newGame() {
    const [gameData, setGameData] = dataArray;
    const { data } = await axios.post(`http://localhost:8080/game/begin/${text}`, text);
    setGameData(data);
  }

  return (
    <div className='start'>
      <div className='nametext'>Enter your name:</div>
      <input className='input' type='text' value={text} onChange={e => setText(e.target.value)} />
      <Link to='/game'>
        <button className='button' onClick={newGame}>Start the game</button>
      </Link>
    </div>
  );
}

export default Start;
