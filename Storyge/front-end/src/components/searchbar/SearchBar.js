import styled from "styled-components";

export const SearchContainer = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #e8e8e8;
  width: 100%;
  height: 40px;
  position: relative;
  margin-top: 20px;
  border-radius: 8px;
`;

export const Icon = styled.div`
  width: 10%;
  margin: 0 10px;
`;

export const Text = styled.input`
  width: 90%;
  height: 36px;
  border: none;
  background-color: transparent;

  &:hover {
    outline: none;
  }

  ::placeholder {
    font-family: "S-CoreDream-4regular";
    font-size: 14px;
    color: #c0c0c0;
  }
`;

export const AutoSearchContainer = styled.div`
  z-index: 1;
  max-height: 50vh;
  width: 100%;
  background-color: #fff;
  position: absolute;
  top: 40px;
  border: 2px solid pink;
  padding: 5px;
  overflow: auto;
  border-radius: 0 0 8px 8px;

  // -webkit-backdrop-filter: blur(30px);
  // backdrop-filter: blur(10px);
`;

export const AutoSearchWrap = styled.div`
  border: 1px solid red;
`;

export const AutoSearchData = styled.div`
  padding: 10px 8px;
  width: 100%;
  font-size: 14px;
  z-index: 4;
  letter-spacing: 2px;
  position: relative;
  border: 1px solid blue;
`;
