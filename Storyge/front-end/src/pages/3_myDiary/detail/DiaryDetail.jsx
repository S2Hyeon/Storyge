import React, { useState } from "react";
import * as S from "../MyDiaryStyle";
import * as A from "styles/index";
import { BsFillCaretDownFill, BsFillCaretUpFill } from "react-icons/bs";

export default function Diarylist() {
  const [isChecked, setIsChecked] = useState(true);
  return (
    <A.BodyContainer>
      <S.Card2 backgroundColor="#EFFCEF">
        <S.MainContent>
          <S.Emotion emotion={S.data[1].img} />
          <S.Col>
            <div>{S.data[1].time}</div>
            <S.Content>{S.data[1].content}</S.Content>
          </S.Col>
        </S.MainContent>
      </S.Card2>
      <S.CardFoot2
        height={isChecked ? "30px" : "120px"}
        backgroundColor="var(--color-primary)"
      >
        <S.Toggle fontSize="14px" onClick={() => setIsChecked((e) => !e)}>
          <S.ToggleBtnBox>
            {isChecked ? (
              <div>
                이 일기의 분석 결과 보기 <BsFillCaretDownFill />
              </div>
            ) : (
              <div>
                이 일기의 분석 결과 닫기 <BsFillCaretUpFill />
              </div>
            )}
          </S.ToggleBtnBox>
        </S.Toggle>
        {isChecked ? null : <S.Mother>{S.data[1].content}</S.Mother>}
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
