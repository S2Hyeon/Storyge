import React from "react";
import { Route, Navigate } from "react-router-dom";
import { isLogin } from "./isLogin.js";

const PublicRoute = ({ component: Component, restricted, ...rest }) => {
  return (
    // restricted = false meaning public route
    // restricted = true meaning restricted route
    <Route
      {...rest}
      render={(props) =>
        isLogin() && restricted ? (
          <Navigate replace to="/main" />
        ) : (
          <Component {...props} />
        )
      }
    />
  );
};

export default PublicRoute;
