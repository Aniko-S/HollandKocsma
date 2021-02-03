import React from 'react';
const cardImages = require.context('../cardImages');

function Card({ id, className, setIds, isSelected, pickUpThePile, isPile, left }) {
  const imageFileName = () => {
    return id + '.png';
  };

  const onClick = () => {
    if (isPile) {
      pickUpThePile();
    } else {
      setIds(id);
    }
  }

  return (
    <img className={isSelected ? `card selected ${className}` : `card ${className}`}
      style={{left:left+'px'}}
      src={cardImages(`./${imageFileName()}`).default}
      alt={imageFileName()} 
      onClick={onClick} />
  );
}

export default Card;

// onClick-ben setIds(id) vagy setIds(0) attol fuggoen, hogy az isPile flag true-e
