import React from 'react';
import BlindCardList from './BlindCardList';
import BlindAndShownCardList from './BlindAndShownCardList';

function Machine({ hand, listShown }) {

  return (
    <>
      <div className='name'>Bob</div>
      <div className='playerLine'>
        <BlindCardList blindNumber={hand} className='hand' />
      </div>
      <div className='playerLine'>
        <BlindAndShownCardList listShown={listShown} />
      </div>
    </>
  );
}

export default Machine;
