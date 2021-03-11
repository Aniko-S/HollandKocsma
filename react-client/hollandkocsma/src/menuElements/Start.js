import React, { useState } from "react";
import { Link } from "react-router-dom";
import axios from "axios";
import Rules from "../Rules";
import Accordion from "../Accordion";

function Start({ dataArray, requestUrl }) {
  const [text, setText] = useState("");

  async function newGame() {
    const name = text || "Player";
    const setGameData = dataArray[1];
    const { data } = await axios.post(`${requestUrl}/game/begin/${name}`, name);
    setGameData(data);
  }

  return (
    <div className="start">
      <h1 className="text-white">
        Welcome in the Shed game. You play against Bob.
      </h1>
      <Accordion title="Rules of the game">
        <Rules />
      </Accordion>
      <h2 className="text-white">Good luck!</h2>
      <div className="d-flex flex-column align-items-center">
        <div className="nametext">Enter your name and start the game.</div>
        <input
          className="input"
          type="text"
          value={text}
          placeholder="Your name"
          onChange={(e) => setText(e.target.value)}
        />
        <Link to="/game">
          <button className="button name" onClick={newGame}>
            Start the game
          </button>
        </Link>
      </div>
    </div>
  );
}

export default Start;
