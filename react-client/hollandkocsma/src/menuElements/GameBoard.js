import React, { useState } from 'react';
import axios from 'axios';
import Machine from '../mainPartitions/Machine';
import Player from '../mainPartitions/Player';
import Table from '../mainPartitions/Table';
import PopUp from '../PopUp';

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

  async function playerPutCardsToShown() {
    const { data } = await axios.post(`http://localhost:8080/game/toshown`, selectedCardsIds);
    setGameData(data);
    setTheButtonsFunction(data);
  }

  async function playerPutCardsToPile() {
    const { data } = await axios.post(`http://localhost:8080/game/game`, selectedCardsIds);
    setGameData(data);
    setSelectedCardsIds([]);
    isGameFinished(data);
    isMachinesTurn(data);
  }

  async function machinePutCardsToPile() {
    const { data } = await axios.get(`http://localhost:8080/game/game`);
    setGameData(data);
    isGameFinished(data);
    isMachinesTurnFinished(data);
  }

  async function playerPickUpThePile() {
    const { data } = await axios.post(`http://localhost:8080/game/game`, [0]);
    setGameData(data);
    setSelectedCardsIds([]);
    isMachinesTurn(data);
  }

  async function playerPutFromBlind() {
    const { data } = await axios.post(`http://localhost:8080/game/game`, [-1]);
    setGameData(data);
    isGameFinished(data);
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

  const isGameFinished = (data) => {
    if (data?.machinesData?.isWinner) {
      console.log("Machine win");
      <PopUp title='Sorry' body='Play one more game' />
    } else if (data?.playersData?.isWinner) {
      console.log("Player win");
      <PopUp title='Congrat' body='Play one more game' />
    }
  }

  return (
   
    <div className='board'>
      {gameData?.machinesData?.isWinner && <PopUp title='Sorry, Bob is the winner' body='Play again, maybe you will have more luck in the next game.' />}
      {gameData?.playersData?.isWinner && <PopUp title='Congratulations, you are the winner' body='Play again to test your lucky' />}
      <div className='playerSpace'>
       {gameData?.machinesData && <Machine hand={gameData.machinesData.handCardsNumber} listShown={gameData.machinesData.shownCardsIds} blindNumber={gameData.machinesData.blindCardsNumber} />}
      </div>
      <div className='tableSpace'>
        {gameData?.tablesData && <Table deck={gameData.tablesData.hasDeck} pile={gameData.tablesData.pileTop} message={gameData.tablesData.message} setIds={setIds} pickUpThePile={playerPickUpThePile} />}
      </div>
      <div className='playerSpace'>
        {gameData?.playersData && <Player name={gameData.playersData.name} listHand={gameData.playersData.handCardsIds} listShown={gameData.playersData.shownCardsIds} blindNumber={gameData.machinesData.blindCardsNumber} setIds={setIds} putCards={filledShown ? playerPutCardsToPile : playerPutCardsToShown} ids={selectedCardsIds} putFromBlind={playerPutFromBlind} />}
      </div>
    </div>
  );
}

export default GameBoard;
