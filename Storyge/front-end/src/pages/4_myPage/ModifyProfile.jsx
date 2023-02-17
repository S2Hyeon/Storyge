import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";

import * as S from "./ModifyProfile";
import ProfileBoxImg from "./../../components/profileBox/ProfileImgBox";
import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";
import { getCookie } from "./../../utils/Cookies";
import { putUser } from "api/user/putUser";
import { getUserCheck } from "api/user/getUserCheck";
import Swal from "sweetalert2";

import Api from "lib/customApi";

export default function ModifyProfile() {
  const movePage = useNavigate();

  const [userNickname, setUserNickname] = useState("");
  const [userImg, setUserImg] = useState("");
  const [userFile, setUserFile] = useState();

  const len = `${userNickname.length} / 8`;

  // 공백(스페이스 바) 체크
  function checkSpace(str) {
    if (str.search(/\s/) !== -1) {
      return true; // 스페이스가 있는 경우
    } else {
      return false; // 스페이스 없는 경우
    }
  }

  // 특수 문자 체크
  let regExp = /[`~!@#$%^&*()_|+\-=?;:'"<>\{\}\[\]\\\/ ]/gim;
  function checkSpecial(str) {
    if (regExp.test(str)) {
      return true;
    } else {
      return false;
    }
  }

  function onChange(e) {
    setUserNickname(e.target.value);
    if (userNickname.length > 7) {
      Swal.fire({
        text: "닉네임은 8자 이내로 작성해주세요.",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "var(--color-primary)",
        cancelButtonColor: "var(--color-warning)",
        confirmButtonText: "Yes",
      });
      setUserNickname(userNickname.substr(0, 7));
    } else if (checkSpecial(e.target.value)) {
      setUserNickname(userNickname.replace(regExp, ""));
      Swal.fire({
        text: "닉네임에 특수문자를 포함할 수 없습니다.",
        icon: "info",
        showCancelButton: true,
        confirmButtonColor: "var(--color-primary)",
        cancelButtonColor: "var(--color-warning)",
        confirmButtonText: "Yes",
      });
    }
  }

  function gomypage() {
    movePage("/mypage");
  }

  async function onsubmit() {
    // 닉네임 중복 검사
    let response = false;
    if (userNickname !== "") {
      response = await getUserCheck(userNickname);
    }
    if (response === true) {
      alert("이미 존재하는 닉네임입니다.");
    } else {
      await putUser(userFile, userNickname);
      gomypage();
    }
  }

  //처음 렌더링이 될 때만 실행
  useEffect(() => {
    async function getUserData() {
      try {
        const response = await Api.get("/user", {
          headers: {
            Authorization: getCookie("token"),
          },
        });
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
          onKeyUpCapture={onChange}
          maxLength="8"
        />
      </Box>
      <S.SubmitBtn onClick={onsubmit}>
        <S.BtnText>등 록</S.BtnText>
      </S.SubmitBtn>
    </S.BodyContainer>
  );
}
