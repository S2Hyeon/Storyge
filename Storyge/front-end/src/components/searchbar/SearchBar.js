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
  border-radius: 8px 8px ${(props) => (props.isFocused ? "0" : "8px")}
    ${(props) => (props.isFocused ? "0" : "8px")};
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
  // border: 2px solid pink;
  z-index: 1;
  max-height: 50vh;
  width: 100%;
  background-color: #e8e8e8;
  position: absolute;
  top: 40px;
  padding: 5px 20px;
  overflow: auto;
  border-radius: 0 0 8px 8px;

  // -webkit-backdrop-filter: blur(30px);
  // backdrop-filter: blur(10px);
`;

export const AutoSearchWrap = styled.div`
  border: 1px solid red;
`;

export const noKeyword = styled.div`
  height: 30px;
  display: flex;
  align-items: center;
`;

export const AutoSearchData = styled.div`
  // border: 1px solid blue;
  padding: 10px 0;
  width: 100%;
  height: 70px;
  font-size: 14px;
  z-index: 4;
  position: relative;
  display: flex;
  align-items: center;

  $:active {
    background-color: red;
  }
`;

export const ProfileImg = styled.div`
  // border: 1px solid red;
  border-radius: 100px;
  width: 50px;
  height: 50px;
  background-image: url(${(props) => props.imgUrl});
  background-size: cover;
  margin: 0 10px 0 0;
`;
