import React, { useState } from 'react';
const cardImages = require.context('./cards');

function Card({ id, className, setIds }) {
  const [isSelected, setIsSelected] = useState(false);

  const select = () => {
    setIsSelected(selected => !selected);
  };

  const imageFileName = () => {
    return id + '.png';
  };
  return (
    <img className={isSelected ? `card selected ${className}` : `card ${className}`}
      src={cardImages(`./${imageFileName()}`).default}
      alt={imageFileName()} 
      onClick={() => {select(); setIds(id);}} />
  );
}

export default Card;
