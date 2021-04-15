import React from "react";
import Card from "./Card";
import BlankCard from "./BlankCard";

function BlindAndShownCard({
  shownId,
  setIds,
  ids,
  putFromBlind,
  blindAvailable,
  shownAvailable,
}) {
  return (
    <span className="blindAndShownCard">
      {shownId > 0 ? (
        <Card
          id={shownId}
          type="shown"
          setIds={setIds}
          isSelected={ids?.includes(shownId)}
          available={shownAvailable}
        />
      ) : (
        <BlankCard
          type="blind"
          putFromBlind={putFromBlind}
          available={blindAvailable}
        />
      )}
    </span>
  );
}

export default BlindAndShownCard;
