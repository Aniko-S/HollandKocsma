import React from "react";
import Collapse from "./Collapse";

function Rules() {
  return (
    <>
      <Collapse title="Start" id="start">
        The game played with one regular 52-card deck. The cards rank highest to
        lowest A, K, Q, J, 10, . . . , 2. Each player is dealt 3 face-down cards
        in a row (blind cards) and 6 hand cards. Each player put down 3 cards
        face-up from 6 hand cards.
      </Collapse>
      <Collapse title="Regular rules" id="regular">
        The first player begins a discard pile on the table, playing face-up
        from his hand any number of cards of the same rank, and taking cards
        from the draw pile to replenish his hand to three cards (drawing happens
        automatically). Then each player must either play a card or a set of
        equal cards face up on top of the discard pile, or pick up the pile. The
        card or cards played must be of equal to or of higher rank than previous
        play (or wildcards). All subsequent players are then to follow this
        rule. If at your turn you cannot or do not wish to play a card, you must
        pick up all the cards in the discard pile and add them to your hand. If
        you pick up you do not play any cards on that turn, but the next player
        starts a new discard pile by playing any card or set of equal cards he
        wishes. Play then continues as before.
      </Collapse>
      <Collapse title="Wildcards" id="wildcards">
        <div>There are wildcards that can be played on any card: 2, 5, 10.</div>
        <div>
          2: Twos may always be played on any card, and any card may be played
          on a two.
        </div>
        <div>
          10: Ten also may always be played on any card. When a ten is played,
          the discard pile is "burned" (removed from play) and the same player
          who played the ten takes another turn, playing any card or set of
          equal cards to start a new discard pile.
        </div>
        <div>
          5: When a five is played, the next play must be lower than or equal to
          five, or a wildcard.
        </div>
      </Collapse>
      <Collapse title="Four cards of the same rank" id="burn">
        If someone completes a set of four cards of the same rank on top of the
        discard pile (either by playing all four cards at once or by equalling
        the previous play), the whole pile is "burned" (removed from play), and
        the same player who completed the four of a kind takes another turn,
        playing any card or set of equal cards to start a new discard pile.
      </Collapse>
      <Collapse title="End of the game" id="end">
        <div>
          After a player has no more cards in his hand, and the deck is empty,
          he needs to play from his three face-up cards. You cannot play from
          this set of cards until you have finished with your hand. The rules
          are the same: the card or cards played must be of equal to or of
          higher rank than previous play or can be wildcards. If at your turn
          you cannot or do not wish to play a card, you must pick up all the
          cards in the discard pile and add them to your hand. Then you are
          required to play your entire hand again before progressing to the rest
          of your face-up cards.
        </div>
        <div>
          Once all of the face-up and hand cards have been played, a player must
          then play his blind cards. These cards are played one at a time,
          without the player knowing the card until the moment it is played. As
          usual, if the chosen card is lower than the previous card played and
          it is not a whildcard, you need to pick up the pile, and are required
          to play your entire hand again before progressing to the rest of your
          face-down cards. The winner is the one who runs out of cards first.
        </div>
      </Collapse>
    </>
  );
}

export default Rules;
