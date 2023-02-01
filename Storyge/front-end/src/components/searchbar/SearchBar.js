import styled from "styled-components";

export const Box = styled.div`
  display: flex;
  width: 350px;
  height: 36px;
  padding: 2px;
  background-color: #E8E8E8;
  border-radius: 8px;
  justify-content: center;
  align-items: center;
`;

export const Icon = styled.div`
  width: 10%;
  `;

export const Text = styled.input`
  width: 90%;
  height: 36px;
  border: none;
  background-color: #E8E8E8;

  &:hover{  
    outline: none;
  }
`;