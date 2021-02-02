import React from 'react';
import CardList from './CardList';
import BlindAndShownCardList from './BlindAndShownCardList';

function Player({ name, listHand, listShown, blindNumber, setIds, putCards }) {

  return (
    <>
      <div className='playerLine'>
        <div className='container'>
          <div className='row align-items-center'>
            <div className='col-3'></div>
            <div className='col-6'>
              <BlindAndShownCardList listShown={listShown} />
            </div>
            <div className='col-3'>
              <button onClick={putCards}>Put</button>
            </div>
          </div>
        </div>
      </div>
      <div className='playerLine'>
        <div className='cardList'>
          <CardList className='hand' cardIdList={listHand} setIds={setIds} />
        </div>
      </div>
      <div className='name'>{name}</div>
    </>
  );
}

export default Player;
