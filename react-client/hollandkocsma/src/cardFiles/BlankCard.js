import React from 'react';
const cardImages = require.context('../cardImages');

function BlankCard({ className, left, putFromBlind }) {
  const imageFileName = () => {
    return 'back.png';
  };
  return (
    <img className={`card ${className}`} 
      style={{left:left+'px'}}
      src={cardImages(`./${imageFileName()}`).default} 
      alt={imageFileName()} 
      onClick={putFromBlind}
    />
  );
}

export default BlankCard;
