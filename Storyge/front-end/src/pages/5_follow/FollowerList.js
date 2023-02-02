import styled from "styled-components";
import './../../../src/App.css'

export const Box = styled.div`
  display: flex;
`;

export const LineText = styled.div`
  display:flex;
	flex-basis:100%;
	align-items:center;
	color: var(--color-primary);
  font-family: "S-CoreDream-3Light";
	font-size:14px;
	margin:8px 0px;

  &::before {
    content:"";
    flex-grow:1;
    margin:0px 16px 0px 0px;
    background: var(--color-primary);
    height:1px;
    font-size:0px;
    line-height: 0px;
  }

  &::after {
    content:"";
    flex-grow:1;
    margin:0px 0px 0px 16px;
    background: var(--color-primary);
    height:1px;
    font-size:0px;
    line-height: 0px;
  }
`;

export const List = styled.div`
  display: flex;
  flex-direction: column;
`;

export const Profile = styled.div`
  display: flex;
  align-items: center;
  margin-bottom: 20px;
`;

export const Img = styled.div`
  height: 60px;
  width: 60px;
  background-image: url(${(props) => props.profile});
  background-size: cover;
  border-radius: 50%;
  margin: 0px 20px 0px 20px;
`;

export const Text = styled.div`
  font-family: "S-CoreDream-4Regular";
	font-size:14px;
  color: var(--color-black);
`;