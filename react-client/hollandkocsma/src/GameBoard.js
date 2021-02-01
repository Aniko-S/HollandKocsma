import React, { useState } from 'react';
import Machine from './Machine';
import Player from './Player';
import Table from './Table';
import axios from 'axios';

function GameBoard({ dataArray }) {
  const [gameData, setGameData] = dataArray;

  const [selectedCardsIds, setSelectedCardsIds] = useState([]);
  const [filledShown, setFilledShown] = useState(false);

  const contains = (id) => {
    for (let i = 0; i < selectedCardsIds.length; i++) {
      if (selectedCardsIds[i] === id) {
        return true;
      }
    }
    return false;
  }

  const deleteId = (id) => {
    setSelectedCardsIds(ids => ids.filter(actId => actId !== id));
  }

  const setIds = (id) => {
    if (contains(id)) {
      deleteId(id);
    } else {
      setSelectedCardsIds([...selectedCardsIds, id]);
    }
  }

  async function putCardsToShown() {
    const { data } = await axios.post(`http://localhost:8080/game/toshown`, selectedCardsIds);
    setGameData(data);
    setFilledShown(true);
  }

  function putCardsToPile() {
    console.log("cat");
  }

  return (
    <div className='board'>
      <div className='playerSpace'>
       {gameData && <Machine hand={gameData.machineHandCardsNumber} listShown={gameData.machineShownCardsIds} blind={gameData.machineBlindCardsNumber} />}
      </div>
      <div className='tableSpace'>
        {gameData && <Table deck={gameData.hasDeck} message={gameData.message} />}
      </div>
      <div className='playerSpace'>
        {gameData && <Player name={gameData.name} listHand={gameData.playersHandCardsIds} listShown={gameData.playersShownCardsIds} blindNumber={gameData.playersBlindCardsNumber} setIds={setIds} putCards={filledShown ? putCardsToPile : putCardsToShown} />}
      </div>
    </div>
  );
}

export default GameBoard;
