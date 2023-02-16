import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";

import * as S from "./ModifyProfile";
import ProfileBoxImg from "./../../components/profileBox/ProfileImgBox";
import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";
import { getCookie } from "./../../utils/Cookies";
import { putUser } from "api/user/putUser";
import { getUserCheck } from "api/user/getUserCheck";

import Api from "lib/customApi";

export default function ModifyProfile() {
  const movePage = useNavigate()

  const [userNickname, setUserNickname] = useState("");
  const [userImg, setUserImg] = useState("");
  const [userFile, setUserFile] = useState();

  const len = `${userNickname.length} / 8`;

  function onChange(e) {
    setUserNickname(e.target.value);
    if (userNickname.length >= 8) {
      alert("닉네임은 8자 이내로 작성해주세요.");
      setUserNickname(userNickname.substring(0, 8));
    }
  }

  function gomypage() {
    movePage("/mypage");
  }

  async function onsubmit() {
    // 닉네임 중복 검사
    let response = await getUserCheck(userNickname);
    if (response === true) {
      alert("이미 존재하는 닉네임입니다.");
    } else {
      putUser(userFile, userNickname);
      gomypage();
    }
  }

  //처음 렌더링이 될 때만 실행
  useEffect(() => {
    async function getUserData() {
      try {
        const response = await Api.get("/user", {
          headers: {
            Authorization: getCookie('token'),
          },
        });
        setUserImg(response.data.profileImg);
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
        />
      </Box>
      <S.SubmitBtn onClick={onsubmit}>등록</S.SubmitBtn>
    </S.BodyContainer>
  )
}
