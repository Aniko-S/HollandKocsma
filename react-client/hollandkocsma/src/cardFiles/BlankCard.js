import React from "react";
const cardImages = require.context("../cardImages");

function BlankCard({ className, left, putFromBlind, available }) {
  const imageFileName = () => {
    return "back.png";
  };
  return (
    <img
      className={`card ${className} ${
        available ? "available" : "disavailable"
      }`}
      style={{ left: left + "px" }}
      src={cardImages(`./${imageFileName()}`).default}
      alt={imageFileName()}
      onClick={available ? putFromBlind : undefined}
    />
  );
}

export default BlankCard;
