import React, { useState } from "react";
import { Link } from "react-router-dom";
import axios from "axios";

function Start({ dataArray }) {
  //const requestUrl = "https://evening-headland-15880.herokuapp.com";
  const requestUrl = "http://localhost:8080";
  const [text, setText] = useState("");

  async function newGame() {
    const name = text || "Player";
    const setGameData = dataArray[1];
    const { data } = await axios.post(`${requestUrl}/game/begin/${name}`, name);
    setGameData(data);
  }

  return (
    <div className="start">
      <h1 className="text-white">Welcome in the game. You play against Bob.</h1>
      <div class="accordion accordion-flush" id="accordionFlushExample">
        <div class="accordion-item">
          <h2 class="accordion-header" id="flush-headingOne">
            <div
              class="accordion-button collapsed text-white bg-transparent arrowButton"
              role="button"
              data-bs-toggle="collapse"
              data-bs-target="#flush-collapseOne"
              aria-expanded="false"
              aria-controls="flush-collapseOne"
            >
              Rules of the game
            </div>
          </h2>
          <div
            id="flush-collapseOne"
            class="accordion-collapse collapse text-white"
            aria-labelledby="flush-headingOne"
            data-bs-parent="#accordionFlushExample"
          >
            <div class="accordion-body">
              Placeholder content for this accordion, which is intended to
              demonstrate the <code>.accordion-flush</code> class. This is the
              first item's accordion body.
            </div>
          </div>
        </div>
      </div>
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
