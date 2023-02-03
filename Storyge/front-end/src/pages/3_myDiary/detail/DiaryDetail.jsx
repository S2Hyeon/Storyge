import React, { useState } from "react";
import * as S from "../MyDiaryStyle";
import * as OwnS from "./DiaryDetailStyle";
import * as G from "styles/index";
import commentData from "./CommentData";
import diaryData from "./DiaryData";
import { BsFillCaretDownFill, BsFillCaretUpFill } from "react-icons/bs";

export default function Diarylist() {
  const [isChecked, setIsChecked] = useState(true);
  return (
    <G.BodyContainer>
      <OwnS.DiaryContainer>
        <OwnS.Diary>
          <OwnS.EmotionContainer>
            <OwnS.Emotion />
          </OwnS.EmotionContainer>
          <OwnS.ContentContiner>
            <OwnS.TimeContainer>
              {/* 맨 오른쪽에 시간, 맨 왼쪽에 수정, 삭제, 공개비공개 */}
              {diaryData.date} {diaryData.time}
            </OwnS.TimeContainer>
            <OwnS.Content>{diaryData.content}</OwnS.Content>
          </OwnS.ContentContiner>
        </OwnS.Diary>
      </OwnS.DiaryContainer>
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
      <OwnS.CommentWriteBox>
        <OwnS.CommentWrite placeholder="댓글 쓰기" />
        <OwnS.submitBtn>작성</OwnS.submitBtn>
      </OwnS.CommentWriteBox>
      {commentData.map((comment) => {
        return (
          <OwnS.ListBox key={comment.id}>
            <OwnS.ProfileImg emotion={comment.img} />
            <S.Col>
              <div>{comment.userName}</div>
              <div>{comment.time}</div>

              <S.Content>{comment.content}</S.Content>
            </S.Col>
          </OwnS.ListBox>
        );
      })}
    </G.BodyContainer>
  );
}
