// src/CourseDetails.js
import React from "react";

const Course = ({ title, duration }) => (
  <li>
    <strong>{title}</strong> - {duration}
  </li>
);

const CourseDetails = () => {
  const courses = [
    { id: "c1", title: "React Fundamentals", duration: "4 weeks" },
    { id: "c2", title: "Advanced JS", duration: "3 weeks" },
    { id: "c3", title: "Fullstack Bootcamp", duration: "6 weeks" },
  ];

  return (
    <div>
      <h2>🎓 Course Details</h2>
      <ul>
        {courses.map((course) => (
          <Course key={course.id} title={course.title} duration={course.duration} />
        ))}
      </ul>
    </div>
  );
};

export default CourseDetails;
