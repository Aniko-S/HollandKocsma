import React, { useState } from "react";
import axios from "axios";
import { useHistory } from "react-router-dom";

function StartMultiPlayer({ requestUrl }) {
  const [text, setText] = useState("");
  const history = useHistory();

  async function newGame() {
    const name = text || "Player";
    const { data } = await axios.post(
      `${requestUrl}/game/multi/create/${name}`,
      name
    );
    console.log(data);
    const id = data;
    history.push(`${id}/created`);
  }

  return (
    <div className="base-container justify-content-center">
      <div className="base-text">Enter your name and create a game</div>
      <input
        className="input"
        type="text"
        value={text}
        placeholder="Your name"
        onChange={(e) => setText(e.target.value)}
      />
      <button className="white-button" onClick={newGame}>
        Create a game
      </button>
    </div>
  );
}

export default StartMultiPlayer;
