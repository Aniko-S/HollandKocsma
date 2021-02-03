import React from 'react';
import CardList from '../cardLists/CardList';
import BlindAndShownCardList from '../cardLists/BlindAndShownCardList';

function Player({ name, listHand, listShown, blindNumber, setIds, putCards, ids, putFromBlind }) {

  return (
    <>
      <div className='playerLine'>
        <div className='container'>
          <div className='row align-items-center'>
            <div className='col-3'></div>
            <div className='col-6'>
              <BlindAndShownCardList listShown={listShown} setIds={setIds} ids={ids} putFromBlind={putFromBlind} blindNumber={blindNumber} />
            </div>
            <div className='col-3'>
              <button onClick={putCards}>Put</button>
            </div>
          </div>
        </div>
      </div>
      <div className='playerLine'>
      <div className='cardList'>
          <CardList className='hand' cardIdList={listHand} setIds={setIds} ids={ids} isCover={true} />
       </div>
      </div>
      <div className='name'>{name}</div>
    </>
  );
}

export default Player;
