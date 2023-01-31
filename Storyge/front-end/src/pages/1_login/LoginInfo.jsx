import React from "react";
import ProfileBox from "./../../components/profileBox/ProfileBox";
import "../1_login/LoginInfo.css";
import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";
import RegisterBtn from "./../../components/button/RegisterBtn";

export default function LoginInfo() {
  return (
    <div className="center">
      <div className="addInfo">
        <h2>Set your Nickname</h2>
        <ProfileBox />
        <Box
          className="box"
          component="form"
          sx={{
            "& > :not(style)": { m: 1, width: "25ch" },
          }}
          noValidate
          autoComplete="off"
        >
          <TextField id="standard-basic" label="NickName" variant="standard" helperText="닉네임을 입력해주세요."/>
        </Box>
        <RegisterBtn />
      </div>
    </div>
  );
}
