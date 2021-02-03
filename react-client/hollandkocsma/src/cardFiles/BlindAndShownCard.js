import React from 'react';
import Card from './Card';
import BlankCard from './BlankCard';

function BlindAndShownCard({ shownId, setIds, ids, putFromBlind }) {
  return (
    <span className='blindAndShown'>
      {shownId > 0 ? 
        <Card id={shownId} 
              className='shown' 
              setIds={setIds} 
              isSelected={ids?.includes(shownId)} 
        /> 
        : <BlankCard className='blind' putFromBlind={putFromBlind} />}
    </span>
  );
}

export default BlindAndShownCard;
