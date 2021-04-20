import React from "react";
import Machine from "../mainPartitions/Machine";
import Player from "../mainPartitions/Player";
import Table from "../mainPartitions/Table";
import PopUp from "../PopUp";
import useGame from "../useGame";

function GameBoard({ dataArray, requestUrl }) {
  const {
    playerPutCardsToPile,
    playerPutCardsToShown,
    playerPickUpThePile,
    playerPutFromBlind,
    isPlayersTurn,
    gameData,
    message,
    setIds,
    filledShown,
    selectedCardsIds,
    buttonText,
    orderByAsc,
  } = useGame(requestUrl, dataArray);

  return (
    <div className="board">
      {gameData?.othersData?.isWinner && (
        <PopUp
          title="Sorry, Bob is the winner"
          body="Play again, maybe you will have more luck in the next game."
        />
      )}
      {gameData?.myData?.isWinner && (
        <PopUp
          title="Congratulations, you are the winner"
          body="Play again to test your luck"
        />
      )}
      <div className="playerSpace">
        {gameData?.othersData && (
          <Machine
            hand={gameData.othersData.handCardsNumber}
            listShown={gameData.othersData.shownCardsIds}
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
        {gameData?.myData && (
          <Player
            name={gameData.myData.name}
            listHand={gameData.myData.handCardsIds}
            listShown={gameData.myData.shownCardsIds}
            setIds={setIds}
            putCards={
              filledShown ? playerPutCardsToPile : playerPutCardsToShown
            }
            ids={selectedCardsIds}
            putFromBlind={playerPutFromBlind}
            isPlayersTurn={isPlayersTurn}
            buttonText={buttonText}
            orderByAsc={orderByAsc}
          />
        )}
      </div>
    </div>
  );
}

export default GameBoard;
