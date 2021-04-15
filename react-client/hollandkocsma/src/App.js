import React, { useState } from "react";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import GameBoard from "./menuElements/GameBoard";
import Start from "./menuElements/Start";
import StartSinglePlayer from "./menuElements/StartSinglePlayer";
import StartMultiPlayer from "./menuElements/StartMultiPlayer";
import "./style.css";

function App() {
  const dataArray = useState();
  // const requestUrl = "http://localhost:8080";
  const requestUrl = "https://evening-headland-15880.herokuapp.com";

  return (
    <Router basename={process.env.PUBLIC_URL}>
      <Switch>
        <Route path="/game">
          <GameBoard dataArray={dataArray} requestUrl={requestUrl} />
        </Route>
        <Route path="/single">
          <StartSinglePlayer dataArray={dataArray} requestUrl={requestUrl} />
        </Route>
        <Route path="/multi">
          <StartMultiPlayer dataArray={dataArray} requestUrl={requestUrl} />
        </Route>
        <Route path="">
          <Start />
        </Route>
      </Switch>
    </Router>
  );
}

export default App;
