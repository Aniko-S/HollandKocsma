import React from "react";
import CardList from "../cardLists/CardList";
import BlindAndShownCardList from "../cardLists/BlindAndShownCardList";

function Player({
  name,
  listHand,
  listShown,
  setIds,
  putCards,
  ids,
  putFromBlind,
  isPlayersTurn,
  buttonText,
  orderByAsc,
}) {
  const shownCardsNumber = listShown.filter((id) => id > 0).length;
  return (
    <>
      <div className="playerLine">
        <div className="container">
          <div className="row align-items-center">
            <div className="col-4"></div>
            <div className="col-4 h-100">
              <BlindAndShownCardList
                listShown={listShown}
                setIds={setIds}
                ids={ids}
                putFromBlind={putFromBlind}
                blindAvailable={
                  isPlayersTurn &&
                  listHand.length === 0 &&
                  shownCardsNumber === 0
                }
                shownAvailable={isPlayersTurn && listHand.length === 0}
              />
            </div>
            <div className="col-4 d-flex">
              <button className="button put mx-3" onClick={putCards}>
                {buttonText}
              </button>
              <button className="button put mx-5" onClick={orderByAsc}>
                Sort
              </button>
            </div>
          </div>
        </div>
      </div>
      <div className="playerLine">
        <div className="d-flex justify-content-center h-100">
          <CardList
            cardType="hand"
            cardIdList={listHand}
            setIds={setIds}
            ids={ids}
            available={isPlayersTurn}
          />
        </div>
      </div>
      <div className="name">{name}</div>
    </>
  );
}

export default Player;
