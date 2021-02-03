import React from 'react';
import BlindAndShownCard from '../cardFiles/BlindAndShownCard';

function BlindAndShownCardList({ listShown, setIds, ids, putFromBlind, blindNumber }) {

  return (
    <div className='cardList'>
      {listShown.map(id => <BlindAndShownCard key={id} shownId={id} setIds={setIds} ids={ids} putFromBlind={putFromBlind} />)}
    </div>
  );
}

export default BlindAndShownCardList;
