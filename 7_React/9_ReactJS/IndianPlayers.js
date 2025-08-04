// src/IndianPlayers.js
import React from "react";

const IndianPlayers = () => {
  const oddTeam = ["Virat", "Dhoni", "Jadeja", "Shami", "Surya"];
  const evenTeam = ["Rohit", "KL Rahul", "Hardik", "Bumrah", "Gill", "Pant"];

  const [captain, ...otherOddPlayers] = oddTeam;
  const [viceCaptain, ...otherEvenPlayers] = evenTeam;

  const T20players = ["Virat", "Rohit", "Gill"];
  const RanjiTrophyPlayers = ["Shaw", "Pujara", "Rahane"];

  const allPlayers = [...T20players, ...RanjiTrophyPlayers];

  return (
    <div>
      <h2>Odd Team Players</h2>
      <p>Captain: {captain}</p>
      <ul>
        {otherOddPlayers.map((player, idx) => <li key={idx}>{player}</li>)}
      </ul>

      <h2>Even Team Players</h2>
      <p>Vice Captain: {viceCaptain}</p>
      <ul>
        {otherEvenPlayers.map((player, idx) => <li key={idx}>{player}</li>)}
      </ul>

      <h2>All Players (Merged)</h2>
      <ul>
        {allPlayers.map((player, idx) => <li key={idx}>{player}</li>)}
      </ul>
    </div>
  );
};

export default IndianPlayers;
