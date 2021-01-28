import React from 'react';
import BlankCard from './BlankCard';
import CardList from './CardList';

function Player({ name, listHand, listShown, blindNumber, setIds, putCards }) {
  const blindCards = [];
  for (let i = 0; i < blindNumber; i++) {
    blindCards.push(<BlankCard className='blind' />);
  }

  return (
    <div className='player'>
      {listShown.length > 0 && <div className='cardList'>
        {listShown && <CardList className='shown' cardIdList={listShown} />}
      </div>}
      <div className='cardList'>
        {blindCards}
        <button onClick={putCards}>Put</button>
      </div>
      <div className='cardList'>
        <CardList className='hand' cardIdList={listHand} setIds={setIds} />
      </div>
    </div>
  );
}

export default Player;
