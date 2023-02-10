import React, { useState, useEffect } from "react";
import axios from "axios";

import * as S from "./ModifyProfile";
import ProfileBoxImg from "./../../components/profileBox/ProfileImgBox";
import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";
import { getCookie } from "./../../utils/Cookies";

export default function ModifyProfile() {
  const [userNickname, setUserNickname] = useState("");
  const [userImg, setUserImg] = useState("");

  const len = `${userNickname.length} / 8`;

  function onChange(e) {
    setUserNickname(e.target.value);
    if (userNickname.length >= 8) {
      alert("닉네임은 8자 이내로 작성해주세요.");
      setUserNickname(userNickname.substr(0, 8));
    }
  }

  // 백 로직 구현되면 다시 확인
  async function onsubmit() {
    try {
      alert("버튼 클릭");
      await axios.put("https://storyge.xyz/api/user", {
        headers: {
          Authorization: getCookie("token"),
        },
        params: {
          nickname: userNickname,
          profile: userImg,
        },
      });
      console.log("put 실행");
    } catch (err) {
      console.log(err);
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
      <S.Text>프로필 수정</S.Text>
      {userImg && <ProfileBoxImg profileImg={userImg} />}
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
      <S.SubmitBtn onClick={onsubmit}>버튼</S.SubmitBtn>
    </S.BodyContainer>
  );
}
