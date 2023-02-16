import styled from "styled-components";

export const Modal = styled.div`
  display: flex;
  flex-direction: column;
  border-radius: 20px;
  border: 3px solid var(--color-primary);
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  background-color: white;
  width: 330px;
  height: 200px;
  justify-content: center;
`;

export const ModalItems = styled.div`
  margin: 20px;
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  align-items: center;
`;

export const ModalBtnDiv = styled.div`
  display: flex;
  justify-content: space-around;
  margin: 20px;
`;

export const EmotionBtn = styled.button`
  display: flex;
  height: 25px;
  width: 25px;
  background-color: var(--color-white);
  border-radius: 100%;
  flex: 0 0 auto;
  justify-content: center;
  align-items: center;
  border: hidden;
  margin: 5px;
`;

export const test = styled.button`
  display: flex;
  height: 25px;
  width: 25px;
  background-color: var(--color-white);
  border-radius: 100%;
  flex: 0 0 auto;
  justify-content: center;
  align-items: center;
  border: hidden;
  box-shadow: 0 0 10px var(--color-${(props) => props.emotion}), 0 0 20px var(--color-${(props) => props.emotion});
  margin: 5px;
`;

export const Emotion = styled.div`
  height: 60px;
  width: 60px;
  background-image: url(${(props) => props.emotion});
  background-size: cover;
  border-radius: 100px;
  flex: 0 0 auto;
  margin: 0 10px 0 0;
`;

export const Col = styled.div`
  margin: 10px 0 10px 0;
  flex-direction: column;
`;

export const Row = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
`;

export const YesBtn = styled.button`
  margin: 0 5px;
  border: none;
  height: 30px;
  width: 70px;
  border-radius: 100px;
  background-color: var(--color-primary);
  font-family: "S-CoreDream-4Regular";
`;

export const NoBtn = styled.button`
  margin: 0 5px;
  border: none;
  height: 30px;
  width: 70px;
  border-radius: 100px;
  background-color: var(--color-warning);
  font-family: "S-CoreDream-4Regular";
`;

export const CancelBtn = styled.button`
  margin: 0 5px;
  border: none;
  height: 30px;
  width: 70px;
  border-radius: 100px;
  background-color: var(--color-grey-light);
  font-family: "S-CoreDream-4Regular";
`;
