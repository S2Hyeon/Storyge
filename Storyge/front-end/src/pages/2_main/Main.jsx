import React from "react";
import Nav from "../../common/footer/Nav";
import Header from "../../common/header/Header";
import * as S from "./MainStyle";
import newDiaryData from "./NewDiaryData";

function Main() {
  return (
    <S.All>
      <Header />

      <S.NewDiary>
        {newDiaryData.map((diary, i) => {
          return <S.Profile diary={diary} key={i} />;
        })}
      </S.NewDiary>
      <S.Container>
        <S.WiseBox>
          <div>
            우리가 출발한 곳은 선택할 수 없지만,
            <br />
            그곳에서 어딜 향해 갈지는 선택할 수 있어.
          </div>
          <div>영화 &lt;월 플라워&gt; 중</div>
        </S.WiseBox>
      </S.Container>

      <Nav />
    </S.All>
  );
}

export default Main;
