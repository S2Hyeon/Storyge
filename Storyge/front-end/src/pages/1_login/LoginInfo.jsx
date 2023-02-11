import React, { useState, useEffect } from "react";
import axios from "axios";
import * as S from "./Loginstyle.js";
import LoginProfileBoxImg from "./../../components/profileBox/LoginProfileImgBox.jsx";
import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";

export default function LoginInfo() {
  const [content, setContent] = useState("");
  const [initData, setInitData] = useState("");
  const len = `${content.length} / 8`;

  useEffect(() => {
    axios
      .get("/user")
      .then((response) => setInitData(response.data))
      .then(console.log(initData))
      .catch((error) => console.log(error));
  }, []);

  function onChange(e) {
    setContent(e.target.value);
    if (content.length >= 8) {
      alert("닉네임은 8자 이내로 작성해주세요.");
      setContent(content.substr(0, 8));
    }
  }

  function onSubmit() {
    alert("추가 정보 등록");
    console.log(content);
  }

  return (
    <S.Login>
      <S.LoginInfoText>Set your Information</S.LoginInfoText>
      <LoginProfileBoxImg />
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
        >
          {initData.nickname}
        </TextField>
      </Box>
      <S.SubmitBtn onClick={onSubmit}>등록</S.SubmitBtn>
    </S.Login>
  );
}
