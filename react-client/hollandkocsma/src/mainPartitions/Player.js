import React, { useState } from 'react';
import CardList from '../cardLists/CardList';
import BlindAndShownCardList from '../cardLists/BlindAndShownCardList';

function Player({ name, listHand, listShown, blindNumber, setIds, putCards, ids, putFromBlind }) {
  const [blindAvaliable, setBlindAvailable] = useState(false);


  return (
    <>
      <div className='playerLine'>
        <div className='container'>
          <div className='row align-items-center'>
            <div className='col-3'></div>
            <div className='col-6'>
              <BlindAndShownCardList listShown={listShown}
                setIds={setIds} ids={ids}
                putFromBlind={putFromBlind}
                blindNumber={blindNumber}
                avaliable={blindAvaliable}
              />
            </div>
            <div className='col-3'>
              <button className='button put' onClick={putCards}>Put</button>
            </div>
          </div>
        </div>
      </div>
      <div className='playerLine'>
        <div className='cardList'>
          <CardList className='hand'
            cardIdList={listHand}
            setIds={setIds}
            ids={ids}
            isCover={true}
          />
        </div>
      </div>
      <div className='name'>{name}</div>
    </>
  );
}

export default Player;
