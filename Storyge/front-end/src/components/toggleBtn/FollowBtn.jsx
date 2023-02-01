import React from 'react';
import * as S from './FollowBtn.js'

export default function FollowBtn() {
  return (
    <S.Box>
      <S.Follower>
        <S.Text>팔로우</S.Text>
      </S.Follower>
      <S.Following>
      <S.Text>팔로잉</S.Text>
      </S.Following>
    </S.Box>
  );
}

