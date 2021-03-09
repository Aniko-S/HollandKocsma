import React from "react";
const cardImages = require.context("../cardImages");

function BlankCard({ className, left, putFromBlind, available }) {
  const imageFileName = () => {
    return "back.png";
  };
  return (
    <img
      className={
        available
          ? `card ${className} available`
          : `card ${className} disavailable`
      }
      style={{ left: left + "px" }}
      src={cardImages(`./${imageFileName()}`).default}
      alt={imageFileName()}
      onClick={available ? putFromBlind : ""}
    />
  );
}

export default BlankCard;
