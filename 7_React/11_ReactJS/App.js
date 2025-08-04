// src/App.js
import React, { Component } from "react";
import CurrencyConvertor from "./CurrencyConvertor";
import "./App.css";

class App extends Component {
  constructor() {
    super();
    this.state = {
      count: 0,
    };
  }

  increment = () => {
    this.setState({ count: this.state.count + 1 });
  };

  sayHello = () => {
    console.log("Hello! Welcome to React Events!");
  };

  increase = () => {
    this.increment();
    this.sayHello();
  };

  decrement = () => {
    this.setState({ count: this.state.count - 1 });
  };

  sayWelcome = (msg) => {
    alert(msg);
  };

  handleClick = (e) => {
    alert("I was clicked! (Synthetic Event)");
    console.log("Synthetic Event Object:", e);
  };

  render() {
    return (
      <div className="App">
        <h1>🎯 React Event Handling Examples</h1>

        <h2>Counter: {this.state.count}</h2>
        <button onClick={this.increase}>Increment</button>
        <button onClick={this.decrement}>Decrement</button>

        <br /><br />
        <button onClick={() => this.sayWelcome("Welcome!")}>Say Welcome</button>

        <br /><br />
        <button onClick={this.handleClick}>Synthetic Event OnPress</button>

        <br /><br />
        <CurrencyConvertor />
      </div>
    );
  }
}

export default App;
