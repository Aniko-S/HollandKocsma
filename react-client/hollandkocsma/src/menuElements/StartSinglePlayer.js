import React, { useState } from "react";
import { Link } from "react-router-dom";
import axios from "axios";
import Rules from "../Rules";
import Accordion from "../Accordion";

function StartSinglePlayer({ dataArray, requestUrl }) {
  const [text, setText] = useState("");

  async function newGame() {
    const name = text || "Player";
    const setGameData = dataArray[1];
    const { data } = await axios.post(
      `${requestUrl}/game/single/begin/${name}`,
      name
    );
    setGameData(data);
  }

  return (
    <div className="start">
      <div className="text-center">
        <h2 className="text-white">You play against Bob.</h2>
        <h2 className="text-white">Good luck!</h2>
      </div>
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
      <Accordion title="Rules of the game">
        <Rules />
      </Accordion>
    </div>
  );
}

export default StartSinglePlayer;
