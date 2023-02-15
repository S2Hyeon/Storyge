import styled from 'styled-components'

export const Container = styled.div`
  // border: 1px solid blue;

  width: 100%;
  min-height: 98px;
  top: 121px;
  left: 22px;

  padding: 10px;

  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  background-color: #d2d9d2;
  justify-content: space-around;
  border-radius: 10px;
  margin: 20px 0 10px 0;
`

export const Box = styled.div`
  display: flex;
  justify-content: space-around;
  align-items: center;
  width: 100%;
`

export const Img = styled.img`
  width: 60px;
  height: 60px;
  border-radius: 50%;
`

export const Name = styled.div`
  font-family: 'S-CoreDream-5Medium';
  font-size: 18px;
`

export const FollowBox = styled.div`
  display: flex;
  justify-content: space-between;
  & > div {
    margin: 10px;
  }
  text-align: center;
`

export const FollowText = styled.div`
  font-family: 'S-CoreDream-4Regular';
  font-size: 18px;
`

export const FollowNumber = styled.div`
  font-family: 'S-CoreDream-4Regular';
  font-size: 16px;
`

export const FollowBtn = styled.button``

export const UnfollowBtn = styled.button``
