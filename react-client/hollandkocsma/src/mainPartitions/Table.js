import React from "react";
import BlankCard from "../cardFiles/BlankCard";
import CardList from "../cardLists/CardList";

function Table({ deck, pile, message, setIds, pickUpThePile, isPlayersTurn }) {
  return (
    <div className="tableLine">
      <div className="container">
        <div className="row align-items-center">
          <div className="col-4 d-flex justify-content-center">
            <textarea rows="3" value={message} readOnly />
          </div>

          <div className="col-2 h-100 d-flex align-items-center justify-content-end">
            {deck && <BlankCard className="deck" avaliable={false} />}
          </div>
          <div className="col-2 h-100 d-flex justify-content-start">
            <CardList
              cardType="pile"
              cardIdList={pile}
              setIds={setIds}
              pickUpThePile={pickUpThePile}
              isPile={true}
              available={isPlayersTurn}
            />
          </div>
          <div className="col-4"></div>
        </div>
      </div>
    </div>
  );
}

export default Table;
