import React from 'react';
import Card from './Card';

function CardList({ cardIdList, className, setIds, ids }) {
  return (
    <div className='cardList'>
      {cardIdList.map(id => <Card className={className} key={id} id={id} setIds={setIds} isSelected={ids?.includes(id)} />)}
    </div>
  );
}

export default CardList;
