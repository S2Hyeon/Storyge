import React from "react";
import { Navigate } from "react-router-dom";
import Swal from "sweetalert2";
import withReactContent from "sweetalert2-react-content";

const MySwal = withReactContent(Swal);

const Toast = MySwal.mixin({
  toast: true,
  position: "center-center",
  showConfirmButton: false,
  timer: 3000,
  timerProgressBar: true,
  didOpen: (toast) => {
    toast.addEventListener("mouseenter", Swal.stopTimer);
    toast.addEventListener("mouseleave", Swal.resumeTimer);
  },
});

const PublicRoute = ({ authenticated, component: Component }) => {
  return authenticated ? (
    <Navigate
      to="/"
      {...Toast.fire({
        icon: "warning",
        title: "이미 로그인되었습니다.",
      })}
    ></Navigate>
  ) : (
    Component
  );
};
export default PublicRoute;
