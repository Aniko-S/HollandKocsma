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
  const [cards, setCards] = useState([]);

  useEffect(() => {
    const difference = listShown.filter((id) => !cards.includes(id));
    if (difference.length !== 1) {
      setCards(listShown);
      return;
    }
    const cardsNotContainsId = difference[0];
    const listShownNotContainsIndex = cards.findIndex(
      (id) => !listShown.includes(id)
    );
    const newCards = [...cards];
    newCards[listShownNotContainsIndex] = cardsNotContainsId;
    setCards(newCards);
  }, [listShown, cards]);

  return (
    <div className="cardList">
      {cards.map((id) => (
        <BlindAndShownCard
          key={id}
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
