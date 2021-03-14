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
}) {
  const shownCardsNumber = listShown.filter((id) => id > 0).length;
  return (
    <>
      <div className="playerLine">
        <div className="container">
          <div className="row align-items-center">
            <div className="col-3"></div>
            <div className="col-6">
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
            <div className="col-3">
              <button className="button put" onClick={putCards}>
                {buttonText}
              </button>
            </div>
          </div>
        </div>
      </div>
      <div className="playerLine">
        <div className={listHand.length > 25 ? "cardList low" : "cardList"}>
          <CardList
            className="hand"
            cardIdList={listHand}
            setIds={setIds}
            ids={ids}
            isCover={true}
            coverWidth={30}
            center={true}
            available={isPlayersTurn}
          />
        </div>
      </div>
      <div className="name">{name}</div>
    </>
  );
}

export default Player;