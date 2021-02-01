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
       {gameData?.machinesData && <Machine hand={gameData.machinesData.handCardsNumber} listShown={gameData.machinesData.shownCardsIds} blind={gameData.machinesData.blindCardsNumber} />}
      </div>
      <div className='tableSpace'>
        {gameData?.tablesData && <Table deck={gameData.tablesData.hasDeck} message={gameData.tablesData.message} />}
      </div>
      <div className='playerSpace'>
        {gameData?.playersData && <Player name={gameData.playersData.name} listHand={gameData.playersData.handCardsIds} listShown={gameData.playersData.shownCardsIds} blindNumber={gameData.machinesData.blindCardsNumber} setIds={setIds} putCards={filledShown ? putCardsToPile : putCardsToShown} />}
      </div>
    </div>
  );
}

export default GameBoard;
