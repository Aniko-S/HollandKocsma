import React from 'react';
import Card from './Card';

function CardList({ cardIdList, className }) {
  return (
    <span>
      {cardIdList.map(id => <Card className={className} key={id} id={id} />)}
    </span>
  );
}

export default CardList;
