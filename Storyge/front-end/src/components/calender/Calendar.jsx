import React, { useState } from "react";
import Calendar from "react-calendar";
import "./Calendar.css"; // css import

function CustomCalendar() {
  const [emoji, setEmoji] = useState([]);

  return <Calendar />;
}

export default CustomCalendar;
