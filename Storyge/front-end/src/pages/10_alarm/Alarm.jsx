import React from 'react';
import * as S from "./AlarmStyle";
import newAlarmData from "./newAlarmData.js";

export default function alarm() {
  return (
    <div>
      <S.Container>
        <S.List>
          {newAlarmData.map((alarm) => {
              return <S.Alarm key={alarm.id}>
                <S.Img profile={alarm.imgUrl}></S.Img>
                <S.Text>{alarm.name}님 팔로우 요청을 보냈습니다.</S.Text>
              </S.Alarm>;
          })}
        </S.List>

      </S.Container>

    </div>
  );
}

