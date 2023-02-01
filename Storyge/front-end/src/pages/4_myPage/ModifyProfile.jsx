import React from 'react';
import * as G from './../../styles/index';
import ProfileBoxImg from './../../components/profileBox/ProfileImgBox';
import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";
import RegisterBtn from "./../../components/button/RegisterBtn";

export default function ModifyProfile() {
  return (
    <G.BodyContainer>
      <ProfileBoxImg />
      <Box
          className="box"
          component="form"
          sx={{
            "& > :not(style)": { m: 1, width: "25ch" },
          }}
          noValidate
          autoComplete="off"
        >
          <TextField id="standard-basic" label="NickName" variant="standard" helperText="닉네임을 입력해주세요." autoFocus="true"/>
        </Box>
        <RegisterBtn />
    </G.BodyContainer>
  );
}

