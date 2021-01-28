import React from 'react';
import Card from './Card';

function CardList({ cardIdList, className, setIds }) {
  return (
    <span>
      {cardIdList.map(id => <Card className={className} key={id} id={id} setIds={setIds} />)}
    </span>
  );
}

export default CardList;
