import React, { useState, useEffect } from 'react';
import axios from "axios";

import * as S from './ModifyProfile';
import ProfileBoxImg from './../../components/profileBox/ProfileImgBox';
import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";
import { getCookie } from "./../../utils/Cookies";

export default function ModifyProfile() {
  const [content, setContent] = useState("");
  const len = `${content.length} / 8`;

  function onChange(e) {
    setContent(e.target.value);
    if (content.length >= 8) {
      alert("닉네임은 8자 이내로 작성해주세요.");
      setContent(content.substr(0, 8));
    }
  }

  const [userData, setUserData] = useState({
    profileImg: '',
    nickname: '',
    follower: '',
    following: '',
  });

    //처음 렌더링이 될 때만 실행
    useEffect(() => {
      async function getUserData() {
        try {
          alert("수정페이지");
          const data = await axios.get("https://storyge.xyz/api/user", {
            headers: {
              Authorization: getCookie("token"),
            },
          });
          console.log("수정페이지");
          setUserData(data);
          console.log(userData);
          console.log('프로필 이미지 : ' + userData.data.profileImg);
          // console.log('닉네임 : ' + userData.data.nickname); 
          // console.log('팔로워 : ' + userData.data.follower); 
        } catch (err) {
          console.log(err);
        }
      }
      getUserData();
    }, []);
  

  return (
    <S.BodyContainer>
      <S.Text>
        프로필 수정
      </S.Text>
      <ProfileBoxImg profileImg={userData.data.profileImg} />
      <Box
          className="box"
          component="form"
          sx={{
            "& > :not(style)": { m: 1, width: "25ch" },
          }}
          noValidate
          autoComplete="off"
        >
        <TextField id="standard-basic" label="NickName" variant="standard" helperText={len} autoFocus={true} onChange={onChange} />
        </Box>
        <S.SubmitBtn>버튼</S.SubmitBtn>
    </S.BodyContainer>
  );
}

