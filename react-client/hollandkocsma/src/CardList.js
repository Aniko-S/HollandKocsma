import React from 'react';
import Card from './Card';

function CardList({ cardIdList, className, setIds }) {
  return (
    <div className='cardList'>
      {cardIdList.map(id => <Card className={className} key={id} id={id} setIds={setIds} />)}
    </div>
  );
}

export default CardList;
