import React, { useState } from "react";
import data from "./DiaryListData";
import * as S from "../MyDiaryStyle";
import * as A from "../../../styles/index";
import { BsFillCaretLeftFill, BsFillCaretRightFill } from "react-icons/bs";

export default function Diarylist() {
  // const [dateInfo, setDateInfo] = useState(new Date());
  // const headDate = `${dateInfo.getFullYear()}.${
  //   dateInfo.getMonth() + 1
  // }.${dateInfo.getDate()}`;
  // const decreaseDate = () => {
  //   setDateInfo(
  //     new Date(
  //       dateInfo.getFullYear(),
  //       dateInfo.getMonth(),
  //       dateInfo.getDate() - 1
  //     )
  //   );
  // };
  // const increaseDate = () => {
  //   setDateInfo(
  //     new Date(
  //       dateInfo.getFullYear(),
  //       dateInfo.getMonth(),
  //       dateInfo.getDate() + 1
  //     )
  //   );
  // };
  // return (
  //   <A.BodyContainer>
  //     <S.DiaryHeader>
  //       <S.AutoBtn onClick={decreaseDate}>
  //         <BsFillCaretLeftFill />
  //       </S.AutoBtn>
  //       <S.DateInfo>{headDate}</S.DateInfo>
  //       <S.AutoBtn onClick={increaseDate}>
  //         <BsFillCaretRightFill />
  //       </S.AutoBtn>
  //     </S.DiaryHeader>
  //     {data.map((data) => {
  //       return (
  //         <S.ListBox key={data.id}>
  //           <S.Emotion emotion={data.img} />
  //           <S.Col>
  //             <div>{data.time}</div>
  //             <S.Content>{data.content}</S.Content>
  //           </S.Col>
  //         </S.ListBox>
  //       );
  //     })}
  //   </A.BodyContainer>
  // );
}
