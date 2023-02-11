import styled from "styled-components";
import "./../../../src/App.css";

export const Container = styled.div`
  width: 100%;
`;

export const Box = styled.div`
  display: flex;
  width: 100%;
  height: 38px;
  margin-top: 20px;
`;

export const AllBox = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  width: 80%;
`;

export const LineText = styled.div`
  display: flex;
  flex-basis: 100%;
  align-items: center;
  color: var(--color-primary);
  font-family: "S-CoreDream-3Light";
  font-size: 14px;
  margin: 8px 0px;
  width: 100%;

  &::before {
    content: "";
    flex-grow: 1;
    margin: 0px 16px 0px 0px;
    background: var(--color-primary);
    height: 1px;
    font-size: 0px;
    line-height: 0px;
  }

  &::after {
    content: "";
    flex-grow: 1;
    margin: 0px 0px 0px 16px;
    background: var(--color-primary);
    height: 1px;
    font-size: 0px;
    line-height: 0px;
  }
`;

export const List = styled.div`
  display: flex;
  flex-direction: column;
`;

export const Profile = styled.div`
  display: flex;
  align-items: center;
  margin-bottom: 20px;
`;

export const Img = styled.div`
  height: 60px;
  width: 60px;
  background-image: url(${(props) => props.profile});
  background-size: cover;
  border-radius: 50%;
  margin-right: 3%;
`;

export const Text = styled.div`
  font-family: "S-CoreDream-4Regular";
  font-size: 14px;
  color: var(--color-black);
  margin: auto;
  text-align: left;
`;

export const FollowBtn = styled.button`
  border-radius: 20px;
  background-color: var(--color-white);
  border: ${(props) => props.borderColor} 1px solid;
  color: ${(props) => props.color};
  box-shadow: none;
  margin: 2px;
`;

export const BtnBox = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  width: 20%;
`;
