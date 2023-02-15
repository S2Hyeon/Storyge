import styled from "styled-components";

export const Header = styled.div`
  height: 60px;
  position: fixed;
  top: 0;
  width: 100%;
  background-color: var(--color-background);
  padding-left: 20px;
  display: flex;
  flex-direction: row;
  align-items: center;
  z-index: 2;

  font-size: 15px;
  font-family: "S-CoreDream-4Regular";
  // font-family: "S-CoreDream-5Medium";
`;

export const Img = styled.img`
  height: 60%;
`;

export const BtnContainer = styled.div`
  width: 30px;
  height: 80%;
  display: flex;
  justify-content: center;
  align-items: center;
  // margin: 0 5px 0 0;
`;

export const TitleContainer = styled.div`
  margin: 0 0 0 5px;
`;
