import React from 'react';
import Machine from './Machine';
import Player from './Player';
import Table from './Table';


function GameBoard() {
  return (
    <div className='board'>
      <Machine />
      <Table />
      <Player />
    </div>
  );
}

export default GameBoard;
