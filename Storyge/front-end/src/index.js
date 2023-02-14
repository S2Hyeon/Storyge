import React, { useState } from "react";
import ReactDOM from "react-dom/client";
import "./index.css";
import App from "./App";
import { Provider } from "react-redux";
import { createStore } from "redux";
// import store from "redux/store/store";

const diaryOwner = "init";
function reducer(state = diaryOwner, action) {
  if (action.type === "other") {
    return action.owner;
  } else if (action.type === "me") {
    return "me";
  } else {
    return state;
  }
}

let store = createStore(reducer);

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  <Provider store={store}>
    <App />
  </Provider>
);
