import { useState } from "react";
import "./App.css";

function App() {

  const [mood, setMood] = useState("");
  const [daySummary, setDaySummary] = useState("");
  const [availableTime, setAvailableTime] = useState("20 minutes");

  const [advice, setAdvice] = useState("");
  const [loading, setLoading] = useState(false);

  async function getAdvice() {

    setLoading(true);
    setAdvice("");

    try {

      const response = await fetch(
        "http://localhost:8080/api/creator/advice",
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json"
          },
          body: JSON.stringify({
            mood,
            daySummary,
            availableTime
          })
        }
      );

      const data = await response.json();

      setAdvice(data.advice);

    } catch {

      setAdvice(
        "Could not connect to Creator Compass backend."
      );

    } finally {

      setLoading(false);

    }
  }

  return (
    <div className="app">

      <div className="container">

        <h1>Creator Compass</h1>

        <p className="subtitle">
          Your private AI advisor for sustainable content creation.
        </p>

        <div className="card">

          <label>
            How are you feeling?
          </label>

          <div className="moods">

            {[
              "😊 Happy",
              "🔥 Energetic",
              "😴 Tired",
              "😵 Overwhelmed",
              "😐 Normal"
            ].map((item) => (

              <button
                key={item}
                onClick={() => setMood(item)}
                className={mood === item ? "selected" : ""}
              >
                {item}
              </button>

            ))}

          </div>


          <label>
            What happened today?
          </label>

          <textarea
            placeholder="Tell Creator Compass what happened today..."
            value={daySummary}
            onChange={(e) => setDaySummary(e.target.value)}
          />


          <label>
            How much time can you spend?
          </label>

          <select
            value={availableTime}
            onChange={(e) => setAvailableTime(e.target.value)}
          >
            <option>10 minutes</option>
            <option>20 minutes</option>
            <option>30 minutes</option>
            <option>1 hour</option>
          </select>


          <button
            className="submit"
            onClick={getAdvice}
            disabled={loading}
          >
            {loading
              ? "Thinking..."
              : "Ask Creator Compass →"}
          </button>

        </div>


        {advice && (

          <div className="result">

            <h2>Creator Compass says</h2>

            <pre>{advice}</pre>

          </div>

        )}

      </div>

    </div>
  );
}

export default App;