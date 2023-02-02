import React, { useState } from "react";
import * as S from "./MyDiaryStyle";
import * as A from "./../../styles/index";
import { BsFillCaretLeftFill, BsFillCaretRightFill } from "react-icons/bs";

export default function Diarylist() {
  const data = [
    {
      id: 1,
      img: "https://t4.daumcdn.net/thumb/R720x0.fjpg/?fname=http://t1.daumcdn.net/brunch/service/user/2hnC/image/yq60UzE-_opOB3-DslxCS1ea3Bk.jpg",
      time: "12:30",
      content:
        "일기란 참 어려운것이다. 외냐하면 어렵기 때문이다. 하지만 해내야한다. 그것이 리액트니깐.",
    },
    {
      id: 2,
      img: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQuJBsS6w0ui7kQeKYHpw9HC5LuVdq-ToWQEQ&usqp=CAU",
      time: "17:31",
      content:
        "드디어 리스트를 어느정도 완성한거 같은데 일단 칸쵸가 너무 맛있다. 오늘도 다이어트는 실패했다 젠장.",
    },
  ];
  const [dateInfo, setDateInfo] = useState(new Date());
  const headDate = `${dateInfo.getFullYear()}.${
    dateInfo.getMonth() + 1
  }.${dateInfo.getDate()}`;

  const decreaseDate = () => {
    setDateInfo(
      new Date(
        dateInfo.getFullYear(),
        dateInfo.getMonth(),
        dateInfo.getDate() - 1
      )
    );
  };
  const increaseDate = () => {
    setDateInfo(
      new Date(
        dateInfo.getFullYear(),
        dateInfo.getMonth(),
        dateInfo.getDate() + 1
      )
    );
  };
  return (
    <A.BodyContainer>
      <S.DiaryHeader>
        <S.AutoBtn onClick={decreaseDate}>
          <BsFillCaretLeftFill />
        </S.AutoBtn>
        <S.DateInfo>{headDate}</S.DateInfo>
        <S.AutoBtn onClick={increaseDate}>
          <BsFillCaretRightFill />
        </S.AutoBtn>
      </S.DiaryHeader>
      {data.map((diary) => {
        return (
          <S.DiaryBox key={diary.id}>
            <S.Col>
              <S.Emotion emotion={diary.img} />
              <S.Row>
                <div>{diary.time}</div>
                <S.Content>{diary.content}</S.Content>
              </S.Row>
            </S.Col>
          </S.DiaryBox>
        );
      })}
    </A.BodyContainer>
  );
}
