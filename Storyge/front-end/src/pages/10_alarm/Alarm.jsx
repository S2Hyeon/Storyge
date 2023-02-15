import React, { useEffect, useState } from 'react'
import * as S from './AlarmStyle'
import * as G from 'styles'
import { getCookie } from './../../utils/Cookies'
import Api from 'lib/customApi'
import { useNavigate } from 'react-router-dom'
import { putMakeReadAlarm } from 'api/alarm/putMakeReadAlarm'
import axios from 'axios'

export default function Alarm() {
  const [userData, setUserData] = useState([])

  const movePage = useNavigate()

  function goDiaryPage(id) {
    movePage(`/diary`, { state: { diaryId: id } })
  }

  function goOtherPage(id) {
    movePage(`/otherpage`, { state: { otherId: id } })
  }

  function goMyFollowList() {
    movePage('/follower')
  }

  //처음 렌더링이 될 때만 실행
  useEffect(() => {
    async function getUserData() {
      try {
        const response = await Api.get('/notification', {
          headers: {
            Authorization: getCookie('token'),
          },
        })
        setUserData(response.data)
      } catch (err) {
        console.log(err)
      }
    }

    getUserData()
  }, [])

  //클릭했을 때 알림 배경 없애기
  async function makeReadAlarm(notificationId, isRead) {
    if (isRead === 0) {
      await putMakeReadAlarm(notificationId)
    }
  }

  return (
    <S.Container>
      {userData && (
        <S.List>
          {userData.map((alarm, key) => {
            if (alarm.notiType === 'WAIT') {
              return (
                <S.Alarm
                  key={key}
                  onClick={() => {
                    makeReadAlarm(alarm.notificationId, alarm.isRead)
                    goMyFollowList()
                  }}
                  isReadColor={alarm.isRead}
                >
                  <S.Img profile={alarm.profileImg}></S.Img>
                  <S.Text>
                    <S.BoldText>{alarm.nickname}</S.BoldText>님이
                    <S.BoldText>팔로우 요청</S.BoldText>을 보냈습니다.
                  </S.Text>
                </S.Alarm>
              )
            } else if (alarm.notiType === 'REVIEW') {
              return (
                <S.Alarm
                  key={key}
                  onClick={() => {
                    makeReadAlarm(alarm.notificationId, alarm.isRead)
                    goDiaryPage(alarm.diaryId)
                  }}
                  isReadColor={alarm.isRead}
                >
                  <S.Img profile={alarm.profileImg}></S.Img>
                  <S.Text>
                    <S.BoldText>{alarm.nickname}</S.BoldText>님이{' '}
                    <S.BoldText>댓글</S.BoldText>을 달았습니다.
                  </S.Text>
                </S.Alarm>
              )
            } else if (alarm.notiType === 'FOLLOW') {
              return (
                <S.Alarm
                  key={key}
                  onClick={() => {
                    makeReadAlarm(alarm.notificationId, alarm.isRead)
                    goOtherPage(alarm.follow)
                  }}
                  isReadColor={alarm.isRead}
                >
                  <S.Img profile={alarm.profileImg}></S.Img>
                  <S.Text>
                    <S.BoldText>{alarm.nickname}</S.BoldText>님이{' '}
                    <S.BoldText>팔로우 요청을 수락</S.BoldText>했습니다.
                  </S.Text>
                </S.Alarm>
              )
            }
          })}
        </S.List>
      )}
    </S.Container>
  )
}
