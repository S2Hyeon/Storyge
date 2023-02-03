import styled from "styled-components";

//없어져야함
export const data = [
  {
    id: 1,
    img: "https://t4.daumcdn.net/thumb/R720x0.fjpg/?fname=http://t1.daumcdn.net/brunch/service/user/2hnC/image/yq60UzE-_opOB3-DslxCS1ea3Bk.jpg",
    time: "12:30",
    content:
      "일기란 참 어려운것이다. 외냐하면 어렵기 때문이다. 하지만 해내야한다. 그것이 리액트니깐.",
  },
  {
    id: 2,
    img: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQuJBsS6w0ui7kQeKYHpw9HC5LuVdq-ToWQEQ&usqp=CAU",
    time: "17:31",
    content:
      "드디어 리스트를 어느정도 완성한거 같은데 일단 칸쵸가 너무 맛있다. 오늘도 다이어트는 실패했다 젠장.",
  },
];

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
  width: 330px;
  height: 200px;
  align-items: center;
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

export const EmotionBtn = styled.div`
  height: 30px;
  width: 30px;
  background-image: url(${(props) => props.emotion});
  background-size: cover;
  border-radius: 100px;
  flex: 0 0 auto;
  margin: 0 10px 0 0;
`;

export const TextBox = styled.textarea`
  text-align: top;
  box-sizing: border-box;
  width: 100%;
  height: 80%;
  border: 3px solid #accebc;
  padding: auto;
  border: none;
  border-radius: 20px 20px 0 0;
  padding: 10px 20px 0 20px;
  font-size: 15px;
  background-color: ${(props) => props.backgroundColor};
`;

export const Mother = styled.div`
  margin: 0 20px 0 20px;
`;

export const Card = styled.div`
  border-radius: 20px;
  border: 3px solid #accebc;
  background-color: ${(props) => props.backgroundColor};
  margin: 20px 0 20px 0;
  width: 100%;
  height: 230px;
`;

export const CardFoot = styled.div`
  height: 10%;
  width: 100%;
  display: flex;
  background-color: ${(props) => props.backgroundColor};
  height: ${(props) => props.height};
  border-radius: 0 0 20px 20px;
  justify-content: space-between;
  align-items: center;
`;

export const CountDiary = styled.div`
  margin: 0 0 0 10px;
`;

export const LockToggle = styled.button`
  background-color: var(--color-primary);
  border: hidden;
  margin: 0 10px 0 0;
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
  width: 100%;
  justify-content: center;
  align-items: center;
  height: 60px;
  border-color: white white var(--color-primary);
  border-style: solid;
  border-width: 1px;
`;

export const DateInfo = styled.div`
  width: 100px;
  text-align: center;
`;

export const DiaryBox = styled.div`
  border-color: white white var(--color-primary);
  border-style: solid;
  border-width: 1px;
  height: 80px;
  top: 50%;
  display: flex;
  justify-content: center;
  align-items: center;
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

export const Time = styled.div`
  font-size: 14px;
`;

export const Content = styled.div`
  font-size: 12px;
`;

export const Toggle = styled.button`
  background-color: var(--color-primary);
  border: hidden;
  font-size: ${(props) => props.fontSize};
  border-radius: 100px;
  height: 30px;
  margin: 0 10px 0 0;
`;

export const Card2 = styled.div`
  border-radius: 20px 20px 0 0;
  border: 3px solid #accebc;
  background-color: ${(props) => props.backgroundColor};
  margin: 20px 0 0 0;
  height: 200px;
  width: 100%;
  // box-sizing: border-box;
`;

export const CardFoot2 = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  border: 3px solid #accebc;
  background-color: ${(props) => props.backgroundColor};
  height: ${(props) => props.height};
  border-radius: 0 0 20px 20px;
`;

export const ToggleBtnBox = styled.div`
  width: 100%;
  display: flex;
  justify-content: right;
`;

export const CommentWriteBox = styled.div`
  background-color: var(--color-grey);
  border-radius: 100px;
  height: 35px;
  width: 100%;
  border: hidden;
  margin: 10px 0 10px 0;
  display: flex;
  justify-content: space-between;
  align-items: center;
`;

export const CommentWrite = styled.input`
  background-color: var(--color-grey);
  border-radius: 100px;
  border: hidden;
  font-size: 16px;
  width: 260px;
  height: 33px;
  margin: 0 0 0 10px;
`;

export const submitBtn = styled.button`
  background-color: var(--color-primary);
  border-radius: 100px;
  border: hidden;
  width: 80px;
  height: 35px;
  font-size: 16px;
`;

export const MainContent = styled.div`
  margin: 10px;
  display: flex;
  align-items: center;
`;
