import styled from "styled-components";

export const Box = styled.div`
  width: 347px;
  height: 98px;
  top: 121px;
  left: 22px;

  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #D2D9D2;
  justify-content: space-around;
  border-radius: 10px;
  margin-top: 20px;
`;

export const Img = styled.img`
  width: 60px;
  height: 60px;
  border-radius: 50%;
`;

export const Name = styled.div`
  font-family: "S-CoreDream-5Medium";
  font-size: 22px;
`;

export const FollowBox = styled.div`
  display: flex;
  justify-content: space-between;
  & > div {
    margin: 10px;
  }
`;

export const FollowText = styled.div`
  font-family: "S-CoreDream-4Regular";
  font-size: 18px;
`;

export const FollowNumber = styled.div`
  font-family: "S-CoreDream-4Regular";
  font-size: 16px;
`;