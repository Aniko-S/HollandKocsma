import React from 'react';
const cardImages = require.context('./cards');

function BlankCard({ className, left }) {
  const imageFileName = () => {
    return 'back.png';
  };
  return (
    <img className={`card ${className}`} 
      style={{left:left+'px'}}
      src={cardImages(`./${imageFileName()}`).default} 
      alt={imageFileName()} />
  );
}

export default BlankCard;
