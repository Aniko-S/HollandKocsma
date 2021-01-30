import React from 'react';
import BlindAndShownCard from './BlindAndShownCard';

function BlindAndShownCardList({ listShown, putCards }) {

  return (
    <div className='cardList'>
      {listShown.map(id => <BlindAndShownCard key={id} shownId={id} />)}
      <button onClick={putCards}>Put</button>
    </div>
  );
}

export default BlindAndShownCardList;
