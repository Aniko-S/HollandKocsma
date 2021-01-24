import React, { useState } from 'react';
import axios from 'axios';
import Machine from './Machine';
import Player from './Player';
import Table from './Table';

const name = {
  name: "Aniko"
};

function GameBoard() {
  const [gameData, setGameData] = useState();

  async function newGame() {
    const { data } = await axios.post('http://localhost:8080/game/begin', name);
    setGameData(data);
  }

  return (
    <div className='board'>
      <button onClick={newGame}>Send</button>
      {gameData && <Machine hand={gameData.machineHandCardsNumber} listShown={gameData.machineShownCardsIds} blind={gameData.machineBlindCardsNumber} />}
      <Table />
      {gameData && <Player listHand={gameData.playersHandCardsIds} listShown={gameData.playersShownCardsIds} blindNumber={gameData.playersBlindCardsNumber} />}
    </div>
  );
}

export default GameBoard;
