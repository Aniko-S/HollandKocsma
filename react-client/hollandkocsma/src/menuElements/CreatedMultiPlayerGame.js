import React from "react";
import { useParams } from "react-router-dom";

function CreatedMultiPlayerGame({ requestUrl }) {
  let { id } = useParams();
  return (
    <div className="base-container justify-content-center">
      <div className="base-text">
        Your friends can join the game at this link:
      </div>
      <div className="base-text">
        {requestUrl}/{id}/join
      </div>
    </div>
  );
}

export default CreatedMultiPlayerGame;
