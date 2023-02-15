import styled from "styled-components";

export const Container = styled.div`
  padding-top: 60px;
  margin-bottom: 70px;
`;

export const Alarm = styled.div`
  height: 65px;
  display: flex;
  align-items: center;
  padding: 0 20px;
  background-color: ${(props) =>
    props.isReadColor === 0 ? "var(--color-grey)" : "transparent"};
`;

export const List = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
`;

export const Img = styled.div`
  height: 40px;
  width: 40px;
  background-image: url(${(props) => props.profile});
  background-size: cover;
  border-radius: 50%;
  margin: 0px 20px 0px 20px;
`;

export const Text = styled.div`
  font-family: "S-CoreDream-3Light";
  font-size: 14px;
  justify-content: center;
  align-items: center;
  color: black;
`;

export const BoldText = styled.text`
  font-family: "S-CoreDream-5Medium";
  font-size: 14px;
`;
