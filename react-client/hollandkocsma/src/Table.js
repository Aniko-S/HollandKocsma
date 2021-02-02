import React from 'react';
import BlankCard from './BlankCard';
import CardList from './CardList';

function Table({ deck, pile, message, setIds, pickUpThePile }) {
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
              <CardList className='pile' cardIdList={pile} setIds={setIds} pickUpThePile={pickUpThePile} isPile={true} />
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Table;
