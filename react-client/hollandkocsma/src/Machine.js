import React from 'react';
import CardList from './CardList';
import BlankCard from './BlankCard';
import Card from './Card';
import BlindCardList from './BlindCardList';

function Machine({ hand, listShown, blind }) {


  return (
    <>
      <div className='name'>Bob</div>
      <div className='playerLine'>
        <BlindCardList blindNumber={hand} className='hand' />
      </div>
      <div className='playerLine'>
        <BlindCardList blindNumber={blind} className='blind' />
      </div>
    </>
  );
}

export default Machine;
