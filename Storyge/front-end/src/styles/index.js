import styled from "styled-components";

export const BodyContainer = styled.div`
  margin: 0 20px;
  // max-width: 500px;
  // display: flex;
  // flex-direction: column;
  // justify-content: center;
  // align-items: center;
`;

export const longBtnDefault = styled.button`
  background-color: var(--color-primary);
  border-radius: 100px;
  height: 48px;
  width: 100%;
  font-size: 18px;
  border: hidden;
`;

export const longBtnDisabled = styled.button`
  background-color: var(--color-darkgrey);
  color: var(--color-white);
  border-radius: 100px;
  height: 48px;
  width: 100%;
  font-size: 18px;
  border: hidden;
`;

export const longBtnBorder = styled.button`
  border: hidden;
  background-color: var(--color-white);
  border-radius: 100px;
  height: 48px;
  width: 100%;
  font-size: 18px;
  border: 3px solid var(--color-primary);
`;

export const profileBox = styled.div``;
