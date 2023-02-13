import styled from "styled-components";
import Select from "react-select";

export const Title = styled.div`
  text-align: center;
  font-family: "S-CoreDream-5Medium";
  font-size: 24px;
  margin: 0 0 20px 0;
`;

export const SelectBox = styled.div`
  display: flex;
  justify-content: center;
`;

export const CustomSelect = styled(Select)`
  width: 100%;
  height: 35px;
  background: white;
  color: gray;
  font-size: 15%;

  font-size: 14px;
  border: none;
  margin: 0 5px;
`;
