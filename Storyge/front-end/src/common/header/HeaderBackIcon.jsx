import React from "react";
import { TbChevronLeft } from "react-icons/tb";
import { useNavigate } from "react-router-dom";
import styled from "styled-components";

function HeaderBackIcon() {
  const navigate = useNavigate();

  const BtnContainer = {
    width: "30px",
    height: "80%",
    display: "flex",
    justifyContent: "center",
    alignItems: "center",
    margin: "0 5px 0 0",
  };

  return (
    <div onClick={() => navigate(-1)} style={BtnContainer}>
      <TbChevronLeft />
    </div>
  );
}

export default HeaderBackIcon;
