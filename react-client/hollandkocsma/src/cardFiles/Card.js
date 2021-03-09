import React from "react";
const cardImages = require.context("../cardImages");

function Card({
  id,
  type,
  setIds,
  isSelected,
  pickUpThePile,
  isPile,
  left,
  available,
}) {
  const imageFileName = () => {
    return id + ".png";
  };

  const handleClick = () => {
    if (isPile) {
      pickUpThePile();
    } else {
      setIds(id);
    }
  };

  return (
    <img
      className={`card ${type} ${isSelected && "selected"} ${
        available ? "available" : "disavailable"
      }`}
      style={{ left: left + "px" }}
      src={cardImages(`./${imageFileName()}`).default}
      alt={imageFileName()}
      onClick={available ? handleClick : undefined}
    />
  );
}

export default Card;
