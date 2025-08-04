// src/App.js
import React from "react";
import ListofPlayers from "./ListofPlayers";
import IndianPlayers from "./IndianPlayers";

function App() {
  const flag = true; // change to false to show IndianPlayers

  return (
    <div className="App">
      <h1>🏏 Welcome to Cricket App 🏏</h1>
      {flag ? <ListofPlayers /> : <IndianPlayers />}
    </div>
  );
}

export default App;
