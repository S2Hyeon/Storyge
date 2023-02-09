import styled from "styled-components";
import "./../../styles/index";

export const Rectangle = styled.textarea`
  width: 100%;
  height: 300px;
  margin-top: 20px;
  margin-bottom: 20px;
  background-color: var(--color-lightgrey);
  border: none;
  border-radius: 20px;
  padding: 20px;
  font-size: 18px;
  outline-color: transparent; //textarea onfocus 됐을 때 보더 없앰
`;

export const Text = styled.div`
  color: var(--color-white);
  font-family: "S-CoreDream-3Light";
  font-size: 18px;
`;
