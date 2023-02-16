import React, { useState, useEffect } from "react";
import { BsFillCaretDownFill, BsFillCaretUpFill } from "react-icons/bs";
import { AiOutlineDelete } from "react-icons/ai";
import { useLocation } from "react-router";

import * as S from './DiaryDetailStyle'
import * as G from 'styles/index'

import Emoji from 'components/emoji/Emoji'
import { getMyDiaryDetail } from 'api/diary/getMyDiaryDetail'
import { getUserId } from 'api/user/getUserId'
import { getComment } from 'api/comment/getComment'
import dayjs from 'dayjs'
import { postComment } from 'api/comment/postComment'
import { deleteReview } from 'api/comment/deleteComment'
import { deleteDiary } from 'api/diary/deleteDiary'
import { putDiaryScope } from 'api/diary/putDiaryScope'
import { useNavigate } from 'react-router-dom'

import Swal from 'sweetalert2'
import withReactContent from 'sweetalert2-react-content'
import { useDispatch } from 'react-redux'

const MySwal = withReactContent(Swal)

const Toast = MySwal.mixin({
  toast: true,
  position: 'center-center',
  showConfirmButton: false,
  timer: 3000,
  timerProgressBar: true,
  didOpen: (toast) => {
    toast.addEventListener('mouseenter', Swal.stopTimer)
    toast.addEventListener('mouseleave', Swal.resumeTimer)
  },
})

export default function DiaryDetail() {
  const movePage = useNavigate()
  async function crud(event) {
    if (event === 'delete') {
      if (
        Swal.fire({
          text: '삭제하시겠습니까?',
          icon: 'warning',
          showCancelButton: true,
          confirmButtonColor: '#3085d6',
          cancelButtonColor: '#d33',
          confirmButtonText: 'Yes',
        }).then((result) => {
          if (result.isConfirmed) {
            Toast.fire({
              icon: 'warning',
              title: '삭제되었습니다.',
            })
            deleteDiary(diaryId)
            movePage(-1)
          }
        })
      ) {
      }
    } else if (event === 'put') {
      movePage('/modifyDiary', { state: { already: myDiaryDetailData } })
    } else {
      // await putDiary();
      setIsOpen(!isOpen);
    }
  }
  const location = useLocation()

  const [userNumber, setUserNumber] = useState('')
  const [isChecked, setIsChecked] = useState(true)
  const [diaryId] = useState(location.state.diaryId) //글 번호
  //다이어리 세부 내용 가져오기
  const [myDiaryDetailData, setMyDiaryDetailData] = useState()
  const [isOpen, setIsOpen] = useState(location.state.scope) // 공개여부;
  const [isOther] = useState(location.state.otherUserId)

  //!리덕스를 이용하여 다른 사람
  const dispatch = useDispatch()
  if (isOther != null) {
    dispatch({ type: "other", owner: location.state.nickname });
  } else {
    dispatch({ type: 'me' })
  }

  useEffect(() => {
    async function getMyUserId() {
      const response = await getUserId();
      setUserNumber(response.userId);
    }

    async function getAndSetMyDiaryDetail() {
      const response = await getMyDiaryDetail(diaryId);
      setMyDiaryDetailData(response);
    }

    getMyUserId()
    getAndSetMyDiaryDetail()
  }, [])

  const [changedCount, setChangedCount] = useState(0)

  const handleChange = () => {
    if (isOpen === 1) {
      putDiaryScope(diaryId, 0)
      setIsOpen(0)
    } else {
      putDiaryScope(diaryId, 1)
      setIsOpen(1)
    }
  }

  //이 다이어리의 댓글들 가져오기
  const [commentData, setCommentData] = useState([])
  useEffect(() => {
    async function getAndSetCommentData() {
      const response = await getComment(diaryId);
      setCommentData(response);
    }
    getAndSetCommentData()
  }, [changedCount])

  //댓글 쓰기
  const [commentInputData, setCommentInputData] = useState('')
  function onChangeCommentInput(e) {
    setCommentInputData(e.target.value)
  }
  //댓글 쓰고 엔터 쳤을때도 되게끔
  function onKeyUpCommentInput(e) {
    if (e.key === 'Enter') {
      writeComment()
    }
  }

  //댓글 쓴거 서버로 보내기
  async function writeComment() {
    if (commentInputData === '') {
      window.alert('댓글 내용이 없습니다!')
    } else {
      await postComment(diaryId, commentInputData)
      setCommentInputData('')
      setChangedCount(changedCount + 1)
    }
  }

  async function deleteComment(reviewId, e) {
    await deleteReview(reviewId);
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
                dayjs(myDiaryDetailData.createdAt).format('YYYY.MM.DD HH:mm')}
            </S.TimeContainer>
            <S.Content>
              {myDiaryDetailData && myDiaryDetailData.diaryContent}
            </S.Content>
          </S.ContentContiner>
        </S.Diary>
      </S.DiaryContainer>
      <S.AnalyzedContainer minHeight={isChecked ? '30px' : '120px'}>
        <S.Toggle fontSize="14px" onClick={() => setIsChecked((e) => !e)}>
          <S.ToggleBtnBox>
            {isChecked ? (
              <div style={{ fontFamily: 'S-CoreDream-5Medium' }}>
                이 일기의 분석 결과 보기 <BsFillCaretDownFill />
              </div>
            ) : (
              <div style={{ fontFamily: 'S-CoreDream-5Medium' }}>
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

      <S.Row>
        {!isOther && myDiaryDetailData && myDiaryDetailData.updateCnt === 0 ? (
          <>
            <S.DeleteBtn onClick={(e) => crud(e.target.value)} value="delete">
              삭제
            </S.DeleteBtn>
            <S.ModifyBtn onClick={(e) => crud(e.target.value)} value="put">
              수정
            </S.ModifyBtn>
            <S.PublicBtn onClick={handleChange}>
              {isOpen === 0 ? "비공개" : "공개"}
            </S.PublicBtn>
          </>
        ) : (
          <>
            <S.DeleteBtn onClick={(e) => crud(e.target.value)} value="delete">
              삭제
            </S.DeleteBtn>
            <S.PublicBtn onClick={handleChange}>
              {isOpen === 0 ? "비공개" : "공개"}
            </S.PublicBtn>
          </>
        )}
      </S.Row>

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
              {comment.userId === userNumber ? (
                <AiOutlineDelete
                  onClick={(e) => {
                    deleteComment(comment.reviewId, e)
                  }}
                  color="var(--color-warning)"
                />
              ) : (
                ''
              )}
            </S.CommentInfo>
            <S.CommentContent>{comment.reviewContent}</S.CommentContent>
          </S.CommentBox>
        )
      })}
    </G.BodyContainer>
  )
}
