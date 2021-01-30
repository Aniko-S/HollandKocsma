import React, { useState } from 'react';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import BlindAndShownCardList from './BlindAndShownCardList';
import GameBoard from './GameBoard';
import Start from './Start';
import './style.css';

function App() {
  const dataArray = useState();

  const shown = [1, 2, 3];

  return (
    <Router>
      <Switch>
        <Route path='/game'>
          <GameBoard dataArray={dataArray} />
        </Route>
        <Route path=''>
          <Start dataArray={dataArray} />
        </Route>
      </Switch>
    </Router>
  );
}

export default App;
