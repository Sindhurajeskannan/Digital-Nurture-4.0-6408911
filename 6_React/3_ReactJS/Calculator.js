import React, { useState } from 'react';
import '../Stylesheets/mystyle.css';

function CalculateScore() {
  const [name, setName] = useState('');
  const [school, setSchool] = useState('');
  const [total, setTotal] = useState('');
  const [goal, setGoal] = useState('');
  const [average, setAverage] = useState(null);

  const calculateAverage = () => {
    if (!isNaN(total) && !isNaN(goal) && goal !== 0) {
      const avg = parseFloat(total) / parseFloat(goal);
      setAverage(avg.toFixed(2));
    } else {
      setAverage("Invalid input");
    }
  };

  return (
    <div className="score-container">
      <h2>Student Score Calculator</h2>
      <input type="text" placeholder="Name" value={name} onChange={e => setName(e.target.value)} />
      <input type="text" placeholder="School" value={school} onChange={e => setSchool(e.target.value)} />
      <input type="number" placeholder="Total Marks" value={total} onChange={e => setTotal(e.target.value)} />
      <input type="number" placeholder="Number of Subjects (Goal)" value={goal} onChange={e => setGoal(e.target.value)} />
      <button onClick={calculateAverage}>Calculate</button>

      {average !== null && (
        <div className="result">
          <p><strong>Name:</strong> {name}</p>
          <p><strong>School:</strong> {school}</p>
          <p><strong>Average Score:</strong> {average}</p>
        </div>
      )}
    </div>
  );
}

export default CalculateScore;
