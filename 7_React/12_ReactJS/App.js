// src/App.js
import React, { Component } from "react";
import GuestPage from "./GuestPage";
import UserPage from "./UserPage";
import "./App.css";

class App extends Component {
  constructor() {
    super();
    this.state = {
      isLoggedIn: false,
    };
  }

  toggleLogin = () => {
    this.setState(prevState => ({
      isLoggedIn: !prevState.isLoggedIn,
    }));
  };

  render() {
    let pageContent;

    // Element variable for conditional rendering
    if (this.state.isLoggedIn) {
      pageContent = <UserPage />;
    } else {
      pageContent = <GuestPage />;
    }

    return (
      <div className="App">
        <h1>🎫 Ticket Booking App</h1>

        <button onClick={this.toggleLogin}>
          {this.state.isLoggedIn ? "Logout" : "Login"}
        </button>

        <hr />

        {/* Conditional Rendering */}
        {pageContent}
      </div>
    );
  }
}

export default App;
