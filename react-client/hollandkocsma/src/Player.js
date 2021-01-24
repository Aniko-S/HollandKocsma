import React from 'react';
import BlankCard from './BlankCard';
import CardList from './CardList';

function Player({ listHand, listShown, blindNumber }) {
  const blindCards = [];
  for (let i = 0; i < blindNumber; i++) {
    blindCards.push(<BlankCard className='blind' />);
  }

  return (
    <>
      <div className='cardList'>
        {listShown && <CardList className='shown' cardIdList={listShown} />}
      </div>
      <div className='cardList'>
        {blindCards}
      </div>
      <div className='cardList'>
        <CardList className='hand' cardIdList={listHand} />
      </div>
    </>
  );
}

export default Player;
