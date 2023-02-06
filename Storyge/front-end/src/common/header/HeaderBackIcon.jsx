import React from "react";
import { TbChevronLeft } from "react-icons/tb";
import { useNavigate } from "react-router-dom";
import { BtnContainer } from "./HeaderStyle";

function HeaderBackIcon() {
  const navigate = useNavigate();

  return (
    <BtnContainer onClick={() => navigate(-1)}>
      <TbChevronLeft />
    </BtnContainer>
  );
}

export default HeaderBackIcon;
//
