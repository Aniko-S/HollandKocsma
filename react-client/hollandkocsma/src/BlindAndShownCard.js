import React from 'react';
import Card from './Card';
import BlankCard from './BlankCard';

function BlindAndShownCard({ shownId }) {
  return (
    <span className='blindAndShown'>
      {shownId > 0 ? <Card id={shownId} className='shown' /> : <BlankCard className='blind' />}
    </span>
  );
}

export default BlindAndShownCard;
