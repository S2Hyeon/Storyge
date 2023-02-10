import React, { useEffect, useState } from "react";
import * as S from "./AlarmStyle";
import { getCookie } from "./../../utils/Cookies";
import Api from "lib/customApi";

export default function Alarm() {
  const [userData, setUserData] = useState({});

  //처음 렌더링이 될 때만 실행
  useEffect(() => {
    async function getUserData() {
      try {
        const response = await Api.get("/notification", {
          headers: {
            Authorization: getCookie("token"),
          },
        });
        console.log("알림페이지");
        console.log(response.data);
        setUserData(response.data);
        console.log(userData);
      } catch (err) {
        console.log(err);
      }
    }
    getUserData();
  }, []);

  return <></>;
}
