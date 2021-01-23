import React from 'react';
import CardList from './CardList';

const cardIdList3 = [50, 13, 14, 22, 11];
const cardIdList4 = [2, 13, 14];

function Player() {
  return (
    <>
      <div className='cardList'>
        <CardList className='shown' cardIdList={cardIdList4} />
      </div>
      <div className='cardList'>
        <CardList className='hand' cardIdList={cardIdList3} />
      </div>
    </>
  );
}

export default Player;
