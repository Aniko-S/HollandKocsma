import React, { useState } from "react";
import axios from "axios";
import { useParams } from "react-router-dom";

function JoinToMultiPlayerGame({ requestUrl }) {
  const [text, setText] = useState("");
  let { id } = useParams();

  async function join() {
    const name = text || "Player";
    const { data } = await axios.post(
      `${requestUrl}/game/multi/${id}/${name}`,
      name
    );
    console.log(data);
  }

  return (
    <div className="base-container justify-content-center">
      <div className="base-text">Enter your name and join to the game</div>
      <input
        className="input"
        type="text"
        value={text}
        placeholder="Your name"
        onChange={(e) => setText(e.target.value)}
      />
      <button className="white-button" onClick={join}>
        Join
      </button>
    </div>
  );
}

export default JoinToMultiPlayerGame;
