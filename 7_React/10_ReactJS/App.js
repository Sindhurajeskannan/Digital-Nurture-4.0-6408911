// src/App.js
import React from "react";
import "./App.css";

function App() {
  // Object of one office
  const singleOffice = {
    name: "BlueSky Office",
    rent: 55000,
    address: "Banjara Hills, Hyderabad",
    image: "https://images.unsplash.com/photo-1588854337119-3e7eeb9f8cda?fit=crop&w=800&q=80"
  };

  // List of office space objects
  const officeList = [
    {
      name: "SkyHub",
      rent: 45000,
      address: "Jubilee Hills, Hyderabad"
    },
    {
      name: "WorkNest",
      rent: 75000,
      address: "Hitech City, Hyderabad"
    },
    {
      name: "CozyDesk",
      rent: 60000,
      address: "Madhapur, Hyderabad"
    }
  ];

  // JSX rendering with condition-based styling
  return (
    <div className="App">
      <h1>🏢 Office Space Rental App</h1>

      {/* Image attribute example */}
      <img
        src={singleOffice.image}
        alt="Office"
        width="500"
        style={{ borderRadius: "10px", marginBottom: "20px" }}
      />

      {/* Render single office */}
      <div style={{ marginBottom: "30px" }}>
        <h2>{singleOffice.name}</h2>
        <p><strong>Address:</strong> {singleOffice.address}</p>
        <p>
          <strong>Rent:</strong>{" "}
          <span style={{ color: singleOffice.rent < 60000 ? "red" : "green" }}>
            ₹{singleOffice.rent}
          </span>
        </p>
      </div>

      {/* Render list of offices */}
      <h2>🏬 Other Available Office Spaces</h2>
      {officeList.map((office, index) => (
        <div
          key={index}
          style={{
            marginBottom: "20px",
            border: "1px solid #ccc",
            borderRadius: "8px",
            padding: "10px",
            width: "400px",
            margin: "10px auto"
          }}
        >
          <h3>{office.name}</h3>
          <p><strong>Address:</strong> {office.address}</p>
          <p>
            <strong>Rent:</strong>{" "}
            <span style={{ color: office.rent < 60000 ? "red" : "green" }}>
              ₹{office.rent}
            </span>
          </p>
        </div>
      ))}
    </div>
  );
}

export default App;
