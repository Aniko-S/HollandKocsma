import React, { useState, useEffect } from "react";
import BlindAndShownCard from "../cardFiles/BlindAndShownCard";

function BlindAndShownCardList({
  listShown,
  setIds,
  ids,
  putFromBlind,
  blindAvailable,
  shownAvailable,
}) {
  const [shownCardsIds, setShownCardsIds] = useState([]);

  useEffect(() => {
    if (shownCardsIds.filter((id) => id > 0).length === 0) {
      setShownCardsIds(listShown);
      return;
    }
    const tempShownCardsIds = shownCardsIds.map((id) =>
      listShown.includes(id) ? id : -1
    );
    setShownCardsIds(tempShownCardsIds);
  }, [listShown]);

  return (
    <div className="cardList">
      {shownCardsIds.map((id, index) => (
        <BlindAndShownCard
          key={index}
          shownId={id}
          setIds={setIds}
          ids={ids}
          putFromBlind={putFromBlind}
          blindAvailable={blindAvailable}
          shownAvailable={shownAvailable}
        />
      ))}
    </div>
  );
}

export default BlindAndShownCardList;
