import React from 'react';
import CardList from './CardList';
import BlankCard from './BlankCard';

const pile = [1, 2, 3];

function Table() {
  return (
    <div className='table'>
      <BlankCard />
      <CardList className='pile' cardIdList={pile} />
    </div>
  );
}

export default Table;
