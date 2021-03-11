import React, { useState } from "react";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import GameBoard from "./menuElements/GameBoard";
import Start from "./menuElements/Start";
import "./style.css";

function App() {
  const dataArray = useState();
  const requestUrl = "https://localhost:8081";
  // const requestUrl = "https://evening-headland-15880.herokuapp.com";

  return (
    <Router basename={process.env.PUBLIC_URL}>
      <Switch>
        <Route path="/game">
          <GameBoard dataArray={dataArray} requestUrl={requestUrl} />
        </Route>
        <Route path="">
          <Start dataArray={dataArray} requestUrl={requestUrl} />
        </Route>
      </Switch>
    </Router>
  );
}

export default App;
