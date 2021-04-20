import React from "react";
import { Link } from "react-router-dom";
import Rules from "../Rules";
import Accordion from "../Accordion";

function Start() {
  return (
    <div className="base-container">
      <h1>Welcome in the Shed game.</h1>
      <Accordion title="Rules of the game">
        <Rules />
      </Accordion>
      <div>
        <Link to="/single">
          <button className="white-button">Singleplayer</button>
        </Link>
        <Link to="/multi/create">
          <button className="white-button">Multiplayer</button>
        </Link>
      </div>
    </div>
  );
}

export default Start;
