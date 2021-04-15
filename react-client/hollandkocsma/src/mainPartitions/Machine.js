import React from "react";
import BlindCardList from "../cardLists/BlindCardList";
import BlindAndShownCardList from "../cardLists/BlindAndShownCardList";

function Machine({ hand, listShown }) {
  return (
    <>
      <div className="name">Bob</div>
      <div className="playerLine">
        <div className="d-flex justify-content-center h-100">
          <BlindCardList blindNumber={hand} cardType="hand" isCover={true} />
        </div>
      </div>
      <div className="playerLine">
        <BlindAndShownCardList listShown={listShown} />
      </div>
    </>
  );
}

export default Machine;
