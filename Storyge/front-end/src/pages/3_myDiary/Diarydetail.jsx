import React, { useState } from "react";
import * as S from "./MyDiaryStyle";
import * as A from "./../../styles/index";
import { BsFillCaretDownFill, BsFillCaretUpFill } from "react-icons/bs";

export default function Diarylist() {
  const [isChecked, setIsChecked] = useState(true);
  return (
    <A.BodyContainer>
      <S.Card2 backgroundColor="#EFFCEF">
        <div>{S.data[0].content}</div>
      </S.Card2>
      <S.CardFoot2
        height={isChecked ? "30px" : "120px"}
        backgroundColor="var(--color-primary)"
      >
        <S.Toggle fontSize="14px" onClick={() => setIsChecked((e) => !e)}>
          {isChecked ? (
            <S.Analysis>
              이 일기의 분석 결과 보기 <BsFillCaretDownFill />
            </S.Analysis>
          ) : (
            <div>
              이 일기의 분석 결과 닫기 <BsFillCaretUpFill />
            </div>
          )}
        </S.Toggle>
        {isChecked ? null : <div>{S.data[1].content}</div>}
      </S.CardFoot2>
      <S.CommentWriteBox>
        <S.CommentWrite placeholder="댓글 쓰기" />
        <S.submitBtn>작성</S.submitBtn>
      </S.CommentWriteBox>
      {S.data.map((diary) => {
        return (
          <S.DiaryBox key={diary.id}>
            <S.Emotion emotion={diary.img} />
            <S.Col>
              <div>갱월쥐</div>
              <div>2023.02.02</div>
              <S.Content>{diary.content}</S.Content>
            </S.Col>
          </S.DiaryBox>
        );
      })}
    </A.BodyContainer>
  );
}
