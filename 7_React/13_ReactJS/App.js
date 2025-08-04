// src/App.js
import React, { Component } from "react";
import BookDetails from "./BookDetails";
import BlogDetails from "./BlogDetails";
import CourseDetails from "./CourseDetails";
import "./App.css";

class App extends Component {
  constructor() {
    super();
    this.state = {
      selected: "book", // Default selection
    };
  }

  changeComponent = (type) => {
    this.setState({ selected: type });
  };

  renderContent = () => {
    // Method 1: if-else
    if (this.state.selected === "book") {
      return <BookDetails />;
    } else if (this.state.selected === "blog") {
      return <BlogDetails />;
    } else if (this.state.selected === "course") {
      return <CourseDetails />;
    } else {
      return <p>Please select a section.</p>;
    }
  };

  render() {
    // Method 2: element variable
    let element = null;
    if (this.state.selected === "blog") {
      element = <BlogDetails />;
    }

    return (
      <div className="App">
        <h1>📘 Blogger App</h1>

        {/* Buttons to switch */}
        <div>
          <button onClick={() => this.changeComponent("book")}>Books</button>
          <button onClick={() => this.changeComponent("blog")}>Blogs</button>
          <button onClick={() => this.changeComponent("course")}>Courses</button>
        </div>

        <hr />

        {/* Method 1: If-else rendering */}
        {this.renderContent()}

        {/* Method 2: Element variable (uncomment to use) */}
        {/* {element} */}

        {/* Method 3: Ternary operator */}
        {/* {this.state.selected === "course" ? <CourseDetails /> : null} */}

        {/* Method 4: Short-circuit rendering */}
        {/* {this.state.selected === "blog" && <BlogDetails />} */}
      </div>
    );
  }
}

export default App;
