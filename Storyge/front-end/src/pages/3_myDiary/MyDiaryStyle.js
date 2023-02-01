import styled from "styled-components";

export const All = styled.div`
  height: 100vh;
  width: 100vw;
  background-color: #eaf2ea;
`;

export const Modal = styled.div`
  border-radius: 20px;
  border: 3px solid #accebc;
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  background-color: white;
  margin: 20px 0 20px 0;
`;

export const TextBox = styled.textarea`
  box-sizing: border-box;
  width: 100%;
  min-height: 100px;
  resize: vertical;
  border: 3px solid #accebc;
  padding: auto;
  border: none;
  margin: 20px 0 20px 0;
  padding: 0 20px 0 20px;
  font-size: 15px;
`;

export const Mother = styled.div`
  margin: 0 20px 0 20px;
`;

export const Card = styled.div`
  border-radius: 20px;
  border: 3px solid #accebc;
  background-color: white;
  margin: 20px 0 20px 0;
  height: 230px;
`;

export const BtnPositive = styled.button`
  background-color: var(--color-primary);
  border-radius: 100px;
  height: 48px;
  width: 100%;
  font-size: 18px;
  border: hidden;
`;
export const BtnNegative = styled.button`
  border: hidden;
  background-color: var(--color-white);
  border-radius: 100px;
  height: 48px;
  width: 100%;
  font-size: 18px;
  border: 3px solid var(--color-primary);
`;

export const CardFoot = styled.div`
  width: 100%;
  display: inline-block;
`;
