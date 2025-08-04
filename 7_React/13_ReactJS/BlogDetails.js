// src/BlogDetails.js
import React from "react";

const BlogDetails = () => {
  const blogs = [
    { id: 101, title: "Intro to React", date: "2025-08-01" },
    { id: 102, title: "Using Hooks Effectively", date: "2025-08-02" },
    { id: 103, title: "Optimizing Performance", date: "2025-08-03" },
  ];

  return (
    <div>
      <h2>📝 Blog Details</h2>
      {blogs.length > 0 ? (
        <ul>
          {blogs.map((blog) => (
            <li key={blog.id}>
              <strong>{blog.title}</strong> - {blog.date}
            </li>
          ))}
        </ul>
      ) : (
        <p>No blogs found.</p>
      )}
    </div>
  );
};

export default BlogDetails;
