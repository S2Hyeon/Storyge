import React, { useState, useEffect } from 'react'
import { useNavigate } from 'react-router-dom'

import * as S from './ModifyProfile'
import ProfileBoxImg from './../../components/profileBox/ProfileImgBox'
import Box from '@mui/material/Box'
import TextField from '@mui/material/TextField'
import { getCookie } from './../../utils/Cookies'
import { putUser } from 'api/user/putUser'
import { getUserCheck } from 'api/user/getUserCheck'

import Api from 'lib/customApi'

export default function ModifyProfile() {
  const movePage = useNavigate()

  const [userNickname, setUserNickname] = useState('')
  const [userImg, setUserImg] = useState('')
  const [userFile, setUserFile] = useState()

  const len = `${userNickname.length} / 8`

  // function onChange(e) {
  //   setUserNickname(e.target.value)

  //   if (userNickname.length >= 8) {
  //     alert('닉네임은 8자 이내로 작성해주세요.')
  //     userNickname = e.target.value.substr(0, 8)
  //   } else if (/[^A-Za-z0-9]/gi.test(userNickname)) {
  //     alert('닉네임에 특수문자를 포함할 수 없습니다.')
  //   }
  // }

  function onChange(str) {
    console.log('Sdfsdf')
    if (str.length < 2 || str.length > 8) {
      alert('2~10자의 한글, 영문, 숫자만 사용할 수 있습니다.')
      return false
    }

    var chk = /[0-9]|[a-z]|[A-Z]|[가-힣]/

    for (var i = 0; i <= str.length - 1; i++) {
      if (chk.test(str.charAt(i))) {
      } else {
        alert('2~8자의 한글, 영문, 숫자만 사용할 수 있습니다.')
        return false
      }
    }

    setUserNickname(str.target.value)
    return true
  }

  function gomypage() {
    movePage('/mypage')
  }

  async function onsubmit() {
    // 닉네임 길이 제한

    // 닉네임 특수 문자 제한

    // 닉네임 중복 검사
    let response = false
    if (userNickname !== '') {
      response = await getUserCheck(userNickname)
    }
    if (response === true) {
      alert('이미 존재하는 닉네임입니다.')
    } else {
      await putUser(userFile, userNickname)
      gomypage()
    }
  }

  //처음 렌더링이 될 때만 실행
  useEffect(() => {
    async function getUserData() {
      try {
        const response = await Api.get('/user', {
          headers: {
            Authorization: getCookie('token'),
          },
        })
        setUserImg(response.data.profileImg)
      } catch (err) {
        console.log(err)
      }
    }
    getUserData()
  }, [])

  return (
    <S.BodyContainer>
      <S.Text>프로필 설정</S.Text>
      {userImg && (
        <ProfileBoxImg profileImg={userImg} modifyFormData={setUserFile} />
      )}
      <Box
        className="box"
        component="form"
        sx={{
          '& > :not(style)': { m: 1, width: '25ch' },
        }}
        noValidate
        autoComplete="off"
      >
        <TextField
          id="standard-basic"
          label="NickName"
          variant="standard"
          helperText={len}
          autoFocus={true}
          onChange={onChange}
          maxLength="8"
        />
      </Box>
      <S.SubmitBtn onClick={onsubmit}>
        <S.BtnText>등 록</S.BtnText>
      </S.SubmitBtn>
    </S.BodyContainer>
  )
}
