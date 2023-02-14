import React, { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

import * as S from "./ModifyProfile";
import ProfileBoxImg from "./../../components/profileBox/ProfileImgBox";
import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";
import { getCookie } from "./../../utils/Cookies";
import { putUser } from "api/user/putUser";
import { getUserCheck } from "api/user/getUserCheck";

export default function ModifyProfile() {
  const movePage = useNavigate();

  const [userNickname, setUserNickname] = useState("");
  const [userImg, setUserImg] = useState("");
  const [userFile, setUserFile] = useState();

  const len = `${userNickname.length} / 8`;

  function onChange(e) {
    setUserNickname(e.target.value);
    if (userNickname.length >= 8) {
      alert("닉네임은 8자 이내로 작성해주세요.");
      setUserNickname(userNickname.substr(0, 8));
    }
  }

  useEffect(() => {
    console.log("수정됨");
    console.log(userFile);
  }, [userFile]);

  function gomypage() {
    movePage("/mypage");
  }

  async function onsubmit() {
    // 닉네임 중복 검사
    let response = await getUserCheck(userNickname);
    if (response === true) {
      alert("이미 존재하는 닉네임입니다.");
    } else {
      console.log("제출 버튼 클릭");
      console.log("이미지 파일 : " + userFile);
      console.log("수정된 닉네임 : " + userNickname);
      putUser(userFile, userNickname);
      alert("프로필 수정 완료");
      gomypage();
    }
  }

  //처음 렌더링이 될 때만 실행
  useEffect(() => {
    async function getUserData() {
      try {
        const response = await axios.get("https://storyge.xyz/api/user", {
          headers: {
            Authorization: getCookie("token"),
          },
        });
        console.log("수정페이지로 이동");
        console.log(response.data);
        setUserImg(response.data.profileImg);
      } catch (err) {
        console.log(err);
      }
    }
    getUserData();
  }, []);

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
          "& > :not(style)": { m: 1, width: "25ch" },
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
  );
}
