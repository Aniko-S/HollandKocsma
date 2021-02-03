import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';

function Start({ dataArray }) {
  const [text, setText] = useState('');

  async function newGame() {
    const name = text || 'Player';  
    const [gameData, setGameData] = dataArray;
    const { data } = await axios.post(`http://localhost:8080/game/begin/${name}`, name);
    setGameData(data);
  }

  return (
    <div className='start'>
      <div className='nametext'>Welcome in the game. You play against Bob.</div>
      <div className='nametext'>Enter your name and start the game.</div>
      <div className='nametext'>Good luck!</div>
      
      <input className='input' type='text' value={text} placeholder='Your name' onChange={e => setText(e.target.value)} />
      <Link to='/game'>
        <button className='button name' onClick={newGame}>Start the game</button>
      </Link>
    </div>
  );
}

export default Start;
