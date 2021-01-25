import React from 'react';
import Machine from './Machine';
import Player from './Player';
import Table from './Table';

function GameBoard({ dataArray }) {
  const [gameData, setGameData] = dataArray;

  return (
    <div className='board'>
      {gameData && <Machine hand={gameData.machineHandCardsNumber} listShown={gameData.machineShownCardsIds} blind={gameData.machineBlindCardsNumber} />}
      {gameData && <Table deck={gameData.hasDeck} />}
      {gameData && <Player name={gameData.name} listHand={gameData.playersHandCardsIds} listShown={gameData.playersShownCardsIds} blindNumber={gameData.playersBlindCardsNumber} />}
    </div>
  );
}

export default GameBoard;
