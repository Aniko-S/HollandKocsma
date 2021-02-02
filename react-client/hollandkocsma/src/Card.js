import React from 'react';
const cardImages = require.context('./cards');

function Card({ id, className, setIds, isSelected }) {
  const imageFileName = () => {
    return id + '.png';
  };
  return (
    <img className={isSelected ? `card selected ${className}` : `card ${className}`}
      src={cardImages(`./${imageFileName()}`).default}
      alt={imageFileName()} 
      onClick={() => setIds(id)} />
  );
}

export default Card;
