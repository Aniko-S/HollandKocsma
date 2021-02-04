import React, {useState, useEffect} from 'react';
import BlindAndShownCard from '../cardFiles/BlindAndShownCard';

function BlindAndShownCardList({ listShown, setIds, ids, putFromBlind, blindAvailable }) {

  const [cards, setCards] = useState([]);

  useEffect(() => {
    const difference = listShown.filter(id => !cards.includes(id));
    if (difference.length !== 1) {
      setCards(listShown);
      return;
    }
    console.log('listShown: ', listShown);
    console.log('cards: ', cards);

    const cardsNotContainsId = difference[0];
    console.log('cardsNotContainsId: ', cardsNotContainsId);
   // const listShownNotContainsIndex = cards.findIndex(() => cards.filter(id => !listShown.includes(id))[0]);

    const listShownNotContainsIndex = cards.findIndex(id => !listShown.includes(id));
    console.log('listShownNotContainsIndex: ', listShownNotContainsIndex);
    const newCards = [...cards];
    newCards[listShownNotContainsIndex] = cardsNotContainsId;
    setCards(newCards);
  }, [listShown]);

  return (
    <div className='cardList'>
      {cards.map(id => 
        <BlindAndShownCard key={id} 
          shownId={id} 
          setIds={setIds} 
          ids={ids} 
          putFromBlind={putFromBlind} 
          blindAvailable={blindAvailable}
        />)}
    </div>
  );
}

export default BlindAndShownCardList;
