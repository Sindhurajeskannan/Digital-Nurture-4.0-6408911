// src/Posts.js
import React, { Component } from 'react';
import Post from './Post';

class Posts extends Component {
  constructor(props) {
    super(props);
    this.state = {
      posts: [],
      hasError: false
    };
  }

  // Fetch data
  loadPosts = () => {
    fetch('https://jsonplaceholder.typicode.com/posts')
      .then(response => {
        if (!response.ok) throw new Error("Failed to fetch");
        return response.json();
      })
      .then(data => {
        const postObjects = data.map(post => new Post(post.userId, post.id, post.title, post.body));
        this.setState({ posts: postObjects });
      })
      .catch(error => {
        this.setState({ hasError: true });
        console.error("Error fetching posts:", error);
      });
  };

  // Lifecycle hook
  componentDidMount() {
    this.loadPosts();
  }

  // Catch rendering errors
  componentDidCatch(error, info) {
    alert("An error occurred in Posts component.");
    console.error("componentDidCatch:", error, info);
  }

  render() {
    if (this.state.hasError) {
      return <h3>Error loading posts.</h3>;
    }

    return (
      <div>
        <h2>Blog Posts</h2>
        {this.state.posts.map(post => (
          <div key={post.id} style={{ border: "1px solid #ccc", margin: "10px", padding: "10px" }}>
            <h3>{post.title}</h3>
            <p>{post.body}</p>
          </div>
        ))}
      </div>
    );
  }
}

export default Posts;
