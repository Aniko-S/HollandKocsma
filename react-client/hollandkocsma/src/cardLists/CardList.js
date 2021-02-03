import React from 'react';
import Card from '../cardFiles/Card';

function CardList({ cardIdList, className, setIds, ids, pickUpThePile, isPile, isCover }) {
  const firstLeft = cardIdList ? ((cardIdList.length - 1) * 30) / 2 : 0;
  return (
    <div className='cardList'>
      {cardIdList.map((id, idx) => 
        <Card className={className} 
              key={id} 
              id={id}
              left={isCover ? firstLeft - idx * 30 : 0}
              setIds={setIds} 
              isSelected={ids?.includes(id)} 
              pickUpThePile={pickUpThePile} 
              isPile={isPile} 
        />)}
    </div>
  );
}

export default CardList;
