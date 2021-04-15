import React from "react";
import BlankCard from "../cardFiles/BlankCard";

function BlindCardList({ blindNumber, cardType }) {
  const blindCards = [];
  for (let i = 0; i < blindNumber; i++) {
    blindCards.push({ id: i });
  }

  return (
    <div className="cardList">
      {blindCards.map((card, idx) => (
        <BlankCard
          key={card.id}
          type={`${cardType} ${idx === blindCards.length - 1 && "last"}`}
        />
      ))}
    </div>
  );
}

export default BlindCardList;
