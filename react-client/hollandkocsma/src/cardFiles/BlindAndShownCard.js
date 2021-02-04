import React from 'react';
import Card from './Card';
import BlankCard from './BlankCard';

function BlindAndShownCard({ shownId, setIds, ids, putFromBlind, blindAvailable }) {
  return (
    <span className='blindAndShown'>
      {shownId > 0 ? 
        <Card id={shownId} 
              className='shown' 
              setIds={setIds} 
              isSelected={ids?.includes(shownId)}
              available={true} 
        /> 
        : <BlankCard className='blind' putFromBlind={putFromBlind} available={blindAvailable} />}
    </span>
  );
}

export default BlindAndShownCard;
