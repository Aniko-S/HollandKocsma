import React from 'react';
import CardList from './CardList';
import BlindCardList from './BlindCardList';
import BlindAndShownCardList from './BlindAndShownCardList';

function Player({ name, listHand, listShown, blindNumber, setIds, putCards }) {

  return (
    <>
      <div className='playerLine'>
        <BlindAndShownCardList listShown={listShown} putCards={putCards} />
        
      </div>
      <div className='playerLine'>
        <CardList className='hand' cardIdList={listHand} setIds={setIds} />
      </div>
    </>
  );
}

export default Player;
