import React from 'react';
const cardImages = require.context('./cards');

function BlankCard({ className }) {
  const imageFileName = () => {
    return 'back.png';
  };
  return (
    <img className={`card ${className}`} src={cardImages(`./${imageFileName()}`).default} alt={imageFileName()} />
  );
}

export default BlankCard;
