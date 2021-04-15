import React from "react";
const cardImages = require.context("../cardImages");

function BlankCard({ type, putFromBlind, available }) {
  const imageFileName = () => {
    return "back.png";
  };
  return (
    <img
      className={`card ${type} ${available ? "available" : "disavailable"}`}
      src={cardImages(`./${imageFileName()}`).default}
      alt={imageFileName()}
      onClick={available ? putFromBlind : undefined}
    />
  );
}

export default BlankCard;
