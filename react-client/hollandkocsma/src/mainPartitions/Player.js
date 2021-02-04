import React, { useState, useEffect } from 'react';
import CardList from '../cardLists/CardList';
import BlindAndShownCardList from '../cardLists/BlindAndShownCardList';

function Player({ name, listHand, listShown, setIds, putCards, ids, putFromBlind }) {
  const [blindAvailable, setBlindAvailable] = useState(false);

  useEffect(() => {
    if (listHand.length === 0) {
      setBlindAvailable(true);
    }
  }, [listHand.length]);

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
                blindAvailable={blindAvailable}
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
            available={true}
          />
        </div>
      </div>
      <div className='name'>{name}</div>
    </>
  );
}

export default Player;
