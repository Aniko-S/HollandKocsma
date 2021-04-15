import React from "react";
import { Link } from "react-router-dom";
import Rules from "../Rules";
import Accordion from "../Accordion";

function Start() {
  return (
    <div className="start">
      <h1 className="text-white">Welcome in the Shed game.</h1>
      <Accordion title="Rules of the game">
        <Rules />
      </Accordion>
      <div>
        <Link to="/single">
          <button className="button name">Singleplayer</button>
        </Link>
        <Link to="/multi">
          <button className="button name">Multiplayer</button>
        </Link>
      </div>
    </div>
  );
}

export default Start;
