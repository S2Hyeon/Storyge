import styled from "styled-components";

export const Box = styled.div`
  display: flex;
  width: 100%;
  height: 10%;
  padding: 2px;
  background-color: #E8E8E8;
  border-radius: 8px;
  justify-content: center;
  align-items: center;
  margin-top: 20px;
  margin-bottom: 20px;
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

  ::placeholder {
    font-family: "S-CoreDream-4regular";
    font-size: 14px;
    color: #C0C0C0;
  }

`;