import React from 'react';
import x from './cards/1.png';
const cardImages = require.context('./cards');

function Card({ id }) {
  const imageFileName = () => {
    return id + '.png';
  };
  return (
    <img src={cardImages(`./${imageFileName()}`).default} alt={imageFileName()} />

  );
}

export default Card;
