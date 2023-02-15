import styled from "styled-components";

export const BodyContainer = styled.div`
  margin: ${(props) => (props.top ? props.top : "60px")} 20px 70px 20px;
  padding: 0 0 ${(props) => (props.bottom ? props.bottom : "0")} 0;
  display: flex;
  flex-direction: column;
  align-items: center;
  // border: 1px solid blue;
`;

export const longBtnDefault = styled.button`
  background-color: var(--color-primary);
  border-radius: 100px;
  height: 48px;
  width: 100%;
  font-size: 18px;
  border: hidden;
  margin-top: 10px;
  color: var(--color-white);
  font-family: "S-CoreDream-4Regular";
  font-size: 16px;
`;

export const longBtnDisabled = styled.button`
  background-color: var(--color-grey);
  color: var(--color-white);
  border-radius: 100px;
  height: 48px;
  width: 100%;
  font-size: 18px;
  border: hidden;
  margin-top: 10px;
  font-family: "S-CoreDream-4Regular";
  font-size: 16px;
`;

export const longBtnBorder = styled.button`
  border: hidden;
  // background-color: var(--color-white);
  background-color: transparent;
  border-radius: 100px;
  height: 48px;
  width: 100%;
  font-size: 18px;
  border: 3px solid var(--color-primary);
  margin-top: 10px;
  color: var(--color-black);
  font-family: "S-CoreDream-4Regular";
  font-size: 16px;
`;
