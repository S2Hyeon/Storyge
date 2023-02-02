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

export const AutoBtn = styled.button`
  width: auto;
  height: auto;
  background-color: var(--color-white);
  border: hidden;
  padding: 3px 0 0 0;
`;

export const DiaryHeader = styled.div`
  display: flex;
  font-size: 18px;
  // position: absolute;
  // left: 50%;
  // top: 80px;
  // transform: translate(-50%, 0);
  justify-content: center;
  align-items: center;
  height: 60px;
  border-color: white white var(--color-primary);
  border-style: solid;
  border-width: 1px;
`;

export const DateInfo = styled.div`
  width: 100px;
`;

export const DiaryBox = styled.div`
  border-color: white white var(--color-primary);
  border-style: solid;
  border-width: 1px;
  height: 80px;
  top: 50%;
`;

export const Emotion = styled.div`
  height: 60px;
  width: 60px;
  margin: 0 5px;
  background-image: url(${(props) => props.emotion});
  background-size: cover;
  border-radius: 100px;

  flex: 0 0 auto;
`;

export const Col = styled.div`
  display: flex;
  margin: 10px 0 10px 0;
`;

export const Row = styled.div`
  display: inline-block;
  text-align: left;
`;

export const Time = styled.div`
  font-size: 14px;
`;

export const Content = styled.div`
  font-size: 12px;
`;
