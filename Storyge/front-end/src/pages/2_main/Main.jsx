import React from "react";
import Nav from "../../common/footer/Nav";
import Header from "../../common/header/Header";
import * as S from "./MainStyle";

function Main() {
  return (
    <>
      <Header />

      <S.NewDiary></S.NewDiary>

      <Nav />
    </>
  );
}

export default Main;
