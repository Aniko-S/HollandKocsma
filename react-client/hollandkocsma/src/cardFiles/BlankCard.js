import React from 'react';
const cardImages = require.context('../cardImages');

function BlankCard({ className, left, putFromBlind, avaliable }) {
  const imageFileName = () => {
    return 'back.png';
  };
  return (
    <img className={avaliable ? `card ${className} available` : `card ${className} disavailable`} 
      style={{left:left+'px'}}
      src={cardImages(`./${imageFileName()}`).default} 
      alt={imageFileName()} 
      onClick={avaliable ? putFromBlind : ''}
    />
  );
}

export default BlankCard;
