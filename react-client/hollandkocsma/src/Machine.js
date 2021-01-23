import React from 'react';
import CardList from './CardList';

const cardIdList = [12, 13, 14];
const cardIdList2 = [12, 13, 40];
function Machine() {
  return (
    <>
      <div className='cardList'>
        <CardList className='hand' cardIdList={cardIdList} />
      </div>
      <div className='cardList'>
        <CardList className='shown' cardIdList={cardIdList2} />
      </div>
    </>
  );
}

export default Machine;
