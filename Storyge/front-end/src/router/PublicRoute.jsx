import React from "react";
import { Navigate } from "react-router-dom";

const PublicRoute = ({ authenticated, component: Component }) => {
  return authenticated ? (
    <Navigate to="/" {...alert("이미 로그인 되었습니다.")}></Navigate>
  ) : (
    Component
  );
};
export default PublicRoute;
