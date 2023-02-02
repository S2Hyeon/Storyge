import React, { useState } from "react";
import * as S from "./MyDiaryStyle";
import * as A from "./../../styles/index";
import { BsFillCaretDownFill, BsFillCaretUpFill } from "react-icons/bs";

export default function Diarylist() {
  const [isChecked, setIsChecked] = useState(false);
  return (
    <A.BodyContainer>
      <S.Col>
        <S.Card2 backgroundColor="#EFFCEF"></S.Card2>
        <S.CardFoot2
          height={isChecked ? "30px" : "120px"}
          backgroundColor="var(--color-primary)"
        >
          <S.Col>
            <S.Toggle fontSize="14px" onClick={() => setIsChecked((e) => !e)}>
              {isChecked ? (
                <div>
                  이 일기의 분석 결과 보기 <BsFillCaretDownFill />
                </div>
              ) : (
                <div>
                  이 일기의 분석 결과 닫기 <BsFillCaretUpFill />
                </div>
              )}
            </S.Toggle>
            {isChecked ? null : <div>{S.data[1].content}</div>}
          </S.Col>
        </S.CardFoot2>
      </S.Col>
    </A.BodyContainer>
  );
}
