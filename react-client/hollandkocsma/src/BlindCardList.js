import React from 'react';
import BlankCard from './BlankCard';

function BlindCardList({ blindNumber, className }) {
  const blindCards = [];
  for (let i = 0; i < blindNumber; i++) {
    blindCards.push({id: i});
  }

  return (
    <div className='cardList'>
      {blindCards.map(id => <BlankCard key={id} className={className} />)}
    </div>
  );
}

export default BlindCardList;
