import React, { useState } from "react";
import axios from "axios";
import Machine from "../mainPartitions/Machine";
import Player from "../mainPartitions/Player";
import Table from "../mainPartitions/Table";
import PopUp from "../PopUp";

function GameBoard({ dataArray, requestUrl }) {
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
    if (data.isBurned) {
      setGameDataFirst(data);
      setTimeout(() => {
        setRealGameDataPlayer(data);
      }, 500);
    } else {
      setRealGameDataPlayer(data);
    }
  }

  async function machinePutCardsToPile() {
    const gameId = gameData.gameId;
    const { data } = await axios.get(`${requestUrl}/game/game/${gameId}`);
    if (data.isBurned) {
      setTimeout(() => {
        setGameDataFirst(data);
        setTimeout(() => {
          setRealGameDataMachine(data);
        }, 500);
      }, 1000);
    } else if (data.isFromBlind) {
      setTimeout(() => {
        const oldGameData = { ...gameData };
        setGameData({
          ...data,
          machinesData: {
            ...data.machinesData,
            handCardsNumber: oldGameData.machinesData.handCardsNumber,
          },
          tablesData: { ...oldGameData.tablesData, pileTop: data.selectedIds },
        });
        setTimeout(() => {
          setRealGameDataMachine(data);
        }, 500);
      }, 1000);
    } else {
      setTimeout(() => {
        setRealGameDataMachine(data);
      }, 1000);
    }
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
    const oldGameData = { ...gameData };
    console.log("blind ", oldGameData);
    setGameData({
      ...data,
      playersData: {
        ...data.playersData,
        handCardsIds: oldGameData.playersData.handCardsIds,
      },
      tablesData: { ...oldGameData.tablesData, pileTop: data.selectedIds },
    });
    setTimeout(() => {
      setRealGameDataPlayer(data);
    }, 500);
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

  const setGameDataFirst = (data) => {
    setGameData({
      ...data,
      tablesData: { ...data.tablesData, pileTop: data.selectedIds },
    });
    setIsPlayersTurn(false);
  };

  const setRealGameDataPlayer = (data) => {
    setGameData(data);
    setSelectedCardsIds([]);
    setIsPlayersTurn(true);
    isMachinesTurn(data);
  };

  const setRealGameDataMachine = (data) => {
    setGameData(data);
    isMachinesTurnFinished(data);
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
