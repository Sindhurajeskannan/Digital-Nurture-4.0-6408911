// src/CurrencyConvertor.js
import React, { Component } from "react";

class CurrencyConvertor extends Component {
  constructor() {
    super();
    this.state = {
      rupees: "",
      euro: "",
    };
  }

  handleChange = (e) => {
    this.setState({ rupees: e.target.value });
  };

  handleSubmit = () => {
    const conversionRate = 0.011; // 1 INR = 0.011 EUR (example)
    const converted = this.state.rupees * conversionRate;
    this.setState({ euro: converted.toFixed(2) });
  };

  render() {
    return (
      <div>
        <h2>💱 Currency Convertor (INR to EUR)</h2>
        <input
          type="number"
          placeholder="Enter amount in ₹"
          value={this.state.rupees}
          onChange={this.handleChange}
        />
        <button onClick={this.handleSubmit}>Convert</button>
        <p>Equivalent in Euro: €{this.state.euro}</p>
      </div>
    );
  }
}

export default CurrencyConvertor;
