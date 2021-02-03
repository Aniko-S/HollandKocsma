import React from 'react';
import BlankCard from '../cardFiles/BlankCard';

function BlindCardList({ blindNumber, className, isCover }) {
  const blindCards = [];
  for (let i = 0; i < blindNumber; i++) {
    blindCards.push({id: i});
  }
  const firstLeft = blindNumber ? ((blindNumber - 1) * 30) / 2 : 0;

  return (
    <div className='cardList'>
      {blindCards.map((id, idx) => 
        <BlankCard key={id} 
                    className={className} 
                    left={isCover ? firstLeft - idx * 30 : 0}
        />)}
    </div>
  );
}

export default BlindCardList;
