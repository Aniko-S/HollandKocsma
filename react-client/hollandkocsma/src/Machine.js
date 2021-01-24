import React from 'react';
import CardList from './CardList';
import BlankCard from './BlankCard';

function Machine({ hand, listShown, blind }) {
  const handCards = [];
  const blindCards = [];
  for (let i = 0; i < hand; i++) {
    handCards.push(<BlankCard className='hand' />);
  }
  for (let i = 0; i < blind; i++) {
    blindCards.push(<BlankCard className='blind' />);
  }

  return (
    <>
      <div className='cardList'>
        {handCards}
      </div>
      <div className='cardList'>
        <CardList className='shown' cardIdList={listShown} />
      </div>
      <div className='cardList'>
        {blindCards}
      </div>
    </>
  );
}

export default Machine;
