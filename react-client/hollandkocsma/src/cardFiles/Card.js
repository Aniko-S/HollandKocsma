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

  const cardClass = available
    ? isSelected
      ? `card selected ${type} available`
      : `card ${type} available`
    : `card ${type} disavailable`;

  return (
    <img
      className={cardClass}
      style={{ left: left + "px" }}
      src={cardImages(`./${imageFileName()}`).default}
      alt={imageFileName()}
      onClick={available ? handleClick : undefined}
    />
  );
}

export default Card;
