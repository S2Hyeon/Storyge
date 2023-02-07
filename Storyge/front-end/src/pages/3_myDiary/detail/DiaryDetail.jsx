import React, { useState } from "react";
import * as S from "./DiaryDetailStyle";
import * as G from "styles/index";
import commentData from "./CommentData";
import diaryData from "./DiaryData";
import analyzedData from "./AnalyzedData";

import { BsFillCaretDownFill, BsFillCaretUpFill } from "react-icons/bs";
import { useLocation } from "react-router";

export default function DiaryDetail() {
  const location = useLocation();

  const [isChecked, setIsChecked] = useState(true);
  const [id] = useState(location.state.id); //글 번호
  console.log("현재 글번호: ", id);

  return (
    <G.BodyContainer>
      <S.DiaryContainer>
        <S.Diary>
          <S.EmotionContainer>
            <S.Emotion />
          </S.EmotionContainer>
          <S.ContentContiner>
            <S.TimeContainer>
              {/* 맨 오른쪽에 시간, 맨 왼쪽에 수정, 삭제, 공개비공개 */}
              {diaryData.date} {diaryData.time}
            </S.TimeContainer>
            <S.Content>{diaryData.content}</S.Content>
          </S.ContentContiner>
        </S.Diary>
      </S.DiaryContainer>
      <S.AnalyzedContainer minHeight={isChecked ? "30px" : "120px"}>
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
        {isChecked ? null : <S.Analyzed>{analyzedData.content}</S.Analyzed>}
      </S.AnalyzedContainer>
      <S.CommentWriteBox>
        <S.CommentWrite placeholder="댓글 쓰기" />
        <S.submitBtn>작성</S.submitBtn>
      </S.CommentWriteBox>
      {commentData.map((comment) => {
        return (
          <S.CommentBox key={comment.id}>
            <S.CommentInfo>
              <S.ProfileImg emotion={comment.img} />
              <S.CommentNameTime>
                <S.CommentName>{comment.userName}</S.CommentName>
                <S.CommentTime>{comment.time}</S.CommentTime>
              </S.CommentNameTime>
            </S.CommentInfo>
            <S.CommentContent>{comment.content}</S.CommentContent>
          </S.CommentBox>
        );
      })}
    </G.BodyContainer>
  );
}
