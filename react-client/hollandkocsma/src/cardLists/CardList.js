import React from "react";
import Card from "../cardFiles/Card";

function CardList({
  cardIdList,
  cardType,
  setIds,
  ids,
  pickUpThePile,
  isPile,
  available,
}) {
  return (
    <div className="cardList">
      {cardIdList?.map((id, idx) => (
        <Card
          key={id}
          id={id}
          type={`${cardType} ${idx === cardIdList.length - 1 && "last"}`}
          setIds={setIds}
          isSelected={ids?.includes(id)}
          pickUpThePile={pickUpThePile}
          isPile={isPile}
          available={available}
        />
      ))}
    </div>
  );
}

export default CardList;
