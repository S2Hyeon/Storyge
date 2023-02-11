import React, { useState, useEffect } from "react";
import { BsFillCaretDownFill, BsFillCaretUpFill } from "react-icons/bs";
import { AiOutlineDelete } from "react-icons/ai";
import { useLocation } from "react-router";

import * as S from "./DiaryDetailStyle";
import * as G from "styles/index";

import Emoji from "components/emoji/Emoji";
import { getMyDiaryDetail } from "api/diary/getMyDiaryDetail";
import { getUserId } from "api/user/getUserId";
import { getComment } from "api/comment/getComment";
import dayjs from "dayjs";
import { postComment } from "api/comment/postComment";
import { deleteReview } from "api/comment/deleteComment";

export default function DiaryDetail() {
  const location = useLocation();

  const [userNumber, setUserNumber] = useState("");
  const [isChecked, setIsChecked] = useState(true);
  const [diaryId] = useState(location.state.diaryId); //글 번호
  console.log("현재 글번호: ", diaryId);

  //다이어리 세부 내용 가져오기
  const [myDiaryDetailData, setMyDiaryDetailData] = useState();
  useEffect(() => {
    async function getMyUserId() {
      const response = await getUserId();
      console.log("유저 번호")
      console.log(response.userId);
      setUserNumber(response.userId);
      console.log("유저 번호 : " + userNumber);
    }

    async function getAndSetMyDiaryDetail() {
      const response = await getMyDiaryDetail(diaryId);
      console.log(response);
      setMyDiaryDetailData(response);
      console.log(myDiaryDetailData);
    }

    getMyUserId();
    getAndSetMyDiaryDetail();
  }, []);

  const [changedCount, setChangedCount] = useState(0);

  //이 다이어리의 댓글들 가져오기
  const [commentData, setCommentData] = useState([]);
  useEffect(() => {
    async function getAndSetCommentData() {
      const response = await getComment(diaryId);
      console.log(response);
      setCommentData(response);
    }
    getAndSetCommentData();
  }, [changedCount]);

  //댓글 쓰기
  const [commentInputData, setCommentInputData] = useState("");
  function onChangeCommentInput(e) {
    setCommentInputData(e.target.value);
  }
  //댓글 쓰고 엔터 쳤을때도 되게끔
  function onKeyUpCommentInput(e) {
    if (e.key == "Enter") {
      writeComment();
    }
  }

  //댓글 쓴거 서버로 보내기
  async function writeComment() {
    if (commentInputData === "") {
      window.alert("댓글 내용이 없습니다!");
    } else {
      await postComment(diaryId, commentInputData);
      setCommentInputData("");
      setChangedCount(changedCount + 1);
    }
  }


  async function deleteComment(reviewId, e) {
    await deleteReview(reviewId);
    console.log("댓글 삭제 완료");
    setChangedCount(changedCount + 1);
  }

  return (
    <G.BodyContainer>
      <S.DiaryContainer>
        <S.Diary>
          <S.EmotionContainer>
            <Emoji
              emotion={myDiaryDetailData && myDiaryDetailData.emoticonName}
              thisWidth="90%"
            />
          </S.EmotionContainer>
          <S.ContentContiner>
            <S.TimeContainer>
              {/* 맨 오른쪽에 시간, 맨 왼쪽에 수정, 삭제, 공개비공개 */}
              {myDiaryDetailData &&
                dayjs(myDiaryDetailData.createdAt).format("YYYY.MM.DD HH:mm")}
            </S.TimeContainer>
            <S.Content>
              {myDiaryDetailData && myDiaryDetailData.diaryContent}
            </S.Content>
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
        {isChecked ? null : (
          <S.Analyzed>
            {myDiaryDetailData && myDiaryDetailData.analizedResult}
          </S.Analyzed>
        )}
      </S.AnalyzedContainer>

      <S.CommentWriteBox>
        <S.CommentWrite
          placeholder="댓글 쓰기"
          value={commentInputData}
          onChange={onChangeCommentInput}
          onKeyUp={onKeyUpCommentInput}
        />
        <S.submitBtn onClick={writeComment}>작성</S.submitBtn>
      </S.CommentWriteBox>

      {commentData.map((comment, index) => {
        return (
          <S.CommentBox key={index}>
            <S.CommentInfo>
              <S.ProfileImg emotion={comment.profileImg} />
              <S.CommentNameTime>
                <S.CommentName>{comment.nickname}</S.CommentName>
                <S.CommentTime>{comment.createdAt}</S.CommentTime>
              </S.CommentNameTime>
              {comment.userId == userNumber ? <AiOutlineDelete onClick={(e) => {
                    deleteComment(comment.reviewId, e);
                  }} /> : ""}
            </S.CommentInfo>
            <S.CommentContent>{comment.reviewContent}</S.CommentContent>
          </S.CommentBox>
        );
      })}
    </G.BodyContainer>
  );
}
