import React, { useState } from "react";
import axios from "axios";
import Machine from "../mainPartitions/Machine";
import Player from "../mainPartitions/Player";
import Table from "../mainPartitions/Table";
import PopUp from "../PopUp";

function GameBoard({ dataArray }) {
  // const requestUrl = "https://evening-headland-15880.herokuapp.com";
  const requestUrl = "http://localhost:8080/";
  const [gameData, setGameData] = dataArray;
  const [isPlayersTurn, setIsPlayersTurn] = useState(true);
  const [buttonText, setButtonText] = useState("Put down");
  const [message, setMessage] = useState("");

  const [selectedCardsIds, setSelectedCardsIds] = useState([]);
  const [filledShown, setFilledShown] = useState(false);

  const contains = (id) => {
    for (let i = 0; i < selectedCardsIds.length; i++) {
      if (selectedCardsIds[i] === id) {
        return true;
      }
    }
    return false;
  };

  const deleteId = (id) => {
    setSelectedCardsIds((ids) => ids.filter((actId) => actId !== id));
  };

  const setIds = (id) => {
    if (contains(id)) {
      deleteId(id);
    } else {
      setSelectedCardsIds([...selectedCardsIds, id]);
    }
  };

  async function playerPutCardsToShown() {
    const gameId = gameData.gameId;
    const { data } = await axios.post(
      `${requestUrl}/game/toshown/${gameId}`,
      selectedCardsIds
    );
    setGameData(data);
    setTheButtonsFunction(data);
    setButtonText("Play");
  }

  async function playerPutCardsToPile() {
    if (selectedCardsIds.length === 0) {
      setMessage(
        "You must select at least one card. You can select a card by clicking on it. If you can't play any card you have to pick up the pile by clicking on it."
      );
      return;
    }
    setMessage("");
    const gameId = gameData.gameId;
    const { data } = await axios.post(
      `${requestUrl}/game/game/${gameId}`,
      selectedCardsIds
    );
    setGameData(data);
    setSelectedCardsIds([]);
    isMachinesTurn(data);
  }

  async function machinePutCardsToPile() {
    const gameId = gameData.gameId;
    const { data } = await axios.get(`${requestUrl}/game/game/${gameId}`);
    setTimeout(() => {
      setGameData(data);
      isMachinesTurnFinished(data);
    }, 1000);
  }

  async function playerPickUpThePile() {
    const gameId = gameData.gameId;
    const { data } = await axios.post(`${requestUrl}/game/game/${gameId}`, [0]);
    setGameData(data);
    setSelectedCardsIds([]);
    isMachinesTurn(data);
  }

  async function playerPutFromBlind() {
    const gameId = gameData.gameId;
    const { data } = await axios.post(`${requestUrl}/game/game/${gameId}`, [
      -1,
    ]);
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
    if (data?.isTurnFinished && !data?.playersData?.isWinner) {
      setIsPlayersTurn(false);
      machinePutCardsToPile();
    }
  };

  const isMachinesTurnFinished = (data) => {
    data?.isTurnFinished ? setIsPlayersTurn(true) : machinePutCardsToPile();
  };

  return (
    <div className="board">
      {gameData?.machinesData?.isWinner && (
        <PopUp
          title="Sorry, Bob is the winner"
          body="Play again, maybe you will have more luck in the next game."
        />
      )}
      {gameData?.playersData?.isWinner && (
        <PopUp
          title="Congratulations, you are the winner"
          body="Play again to test your luck"
        />
      )}
      <div className="playerSpace">
        {gameData?.machinesData && (
          <Machine
            hand={gameData.machinesData.handCardsNumber}
            listShown={gameData.machinesData.shownCardsIds}
            blindNumber={gameData.machinesData.blindCardsNumber}
          />
        )}
      </div>
      <div className="tableSpace">
        {gameData?.tablesData && (
          <Table
            deck={gameData.tablesData.hasDeck}
            pile={gameData.tablesData.pileTop}
            message={message || gameData.tablesData.message}
            setIds={setIds}
            pickUpThePile={playerPickUpThePile}
            isPlayersTurn={isPlayersTurn}
          />
        )}
      </div>
      <div className="playerSpace">
        {gameData?.playersData && (
          <Player
            name={gameData.playersData.name}
            listHand={gameData.playersData.handCardsIds}
            listShown={gameData.playersData.shownCardsIds}
            setIds={setIds}
            putCards={
              filledShown ? playerPutCardsToPile : playerPutCardsToShown
            }
            ids={selectedCardsIds}
            putFromBlind={playerPutFromBlind}
            isPlayersTurn={isPlayersTurn}
            buttonText={buttonText}
          />
        )}
      </div>
    </div>
  );
}

export default GameBoard;
