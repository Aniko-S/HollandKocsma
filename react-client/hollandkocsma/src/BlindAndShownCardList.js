import React from 'react';
import BlindAndShownCard from './BlindAndShownCard';

function BlindAndShownCardList({ listShown }) {

  return (
    <div className='cardList'>
      {listShown.map(id => <BlindAndShownCard key={id} shownId={id} />)}
    </div>
  );
}

export default BlindAndShownCardList;
