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
    setTheButtonsFunction(data);
  }

  async function putCardsToPile() {
    const { data } = await axios.post(`http://localhost:8080/game/game`, selectedCardsIds);
    setGameData(data);
    setSelectedCardsIds([]);
    isMachinesTurn(data);
  }

  async function machinePutCardsToPile() {
    const { data } = await axios.get(`http://localhost:8080/game/game`);
    setGameData(data);
    isMachinesTurnFinished(data);
  }

  async function pickUpThePile() {
    const { data } = await axios.post(`http://localhost:8080/game/game`, [0]);
    setGameData(data);
    setSelectedCardsIds([]);
    isMachinesTurn(data);
  }

  async function putFromBlind() {
    const { data } = await axios.post(`http://localhost:8080/game/game`, [-1]);
    setGameData(data);
    isMachinesTurn(data);
  }

  const setTheButtonsFunction = (data) => {
    if (data?.isTurnFinished) {
      setFilledShown(true);
      setSelectedCardsIds([]);
    }
  };

  const isMachinesTurn = (data) => {
    if (data?.isTurnFinished) {
      machinePutCardsToPile();
    }
  }

  const isMachinesTurnFinished = (data) => {
    if (!data?.isTurnFinished) {
      machinePutCardsToPile();
    }
  }

  return (
    <div className='board'>
      <div className='playerSpace'>
       {gameData?.machinesData && <Machine hand={gameData.machinesData.handCardsNumber} listShown={gameData.machinesData.shownCardsIds} blind={gameData.machinesData.blindCardsNumber} />}
      </div>
      <div className='tableSpace'>
        {gameData?.tablesData && <Table deck={gameData.tablesData.hasDeck} pile={gameData.tablesData.pileTop} message={gameData.tablesData.message} setIds={setIds} pickUpThePile={pickUpThePile} />}
      </div>
      <div className='playerSpace'>
        {gameData?.playersData && <Player name={gameData.playersData.name} listHand={gameData.playersData.handCardsIds} listShown={gameData.playersData.shownCardsIds} blindNumber={gameData.machinesData.blindCardsNumber} setIds={setIds} putCards={filledShown ? putCardsToPile : putCardsToShown} ids={selectedCardsIds} putFromBlind={putFromBlind} />}
      </div>
    </div>
  );
}

export default GameBoard;
