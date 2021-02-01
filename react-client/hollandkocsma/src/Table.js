import React from 'react';
import BlankCard from './BlankCard';
import Card from './Card';

function Table({ deck, message }) {
  return (
    <div className='tableLine'>
      <div className='container'>
        <div className='row align-items-center'>
          <div className='col-3'>
          <textarea rows='3' value={message} readOnly />
          </div>
          <div className='col-6'>
            <div className='cardList'>
              {deck && <BlankCard className='deck' />}
              <Card id='5' />
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Table;
