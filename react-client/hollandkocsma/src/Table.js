import React from 'react';
import CardList from './CardList';
import BlankCard from './BlankCard';
import Card from './Card';

const pile = [1, 2, 3];

function Table({ deck }) {
  return (
    <div className='tableLine'>
      <div className='cardList'>
        <Card id='5' />
      </div>
    </div>
  );
}

export default Table;
