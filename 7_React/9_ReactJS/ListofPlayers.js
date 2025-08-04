// src/ListofPlayers.js
import React from "react";

const ListofPlayers = () => {
  const players = [
    { name: "Virat", score: 85 },
    { name: "Rohit", score: 95 },
    { name: "Dhoni", score: 60 },
    { name: "KL Rahul", score: 45 },
    { name: "Hardik", score: 76 },
    { name: "Jadeja", score: 55 },
    { name: "Shami", score: 40 },
    { name: "Bumrah", score: 73 },
    { name: "Gill", score: 90 },
    { name: "Surya", score: 68 },
    { name: "Pant", score: 82 },
  ];

  const filteredPlayers = players.filter(player => player.score < 70);

  return (
    <div>
      <h2>All Players</h2>
      <ul>
        {players.map((player, index) => (
          <li key={index}>{player.name} - {player.score}</li>
        ))}
      </ul>

      <h2>Players with score below 70</h2>
      <ul>
        {filteredPlayers.map((player, index) => (
          <li key={index}>{player.name} - {player.score}</li>
        ))}
      </ul>
    </div>
  );
};

export default ListofPlayers;
