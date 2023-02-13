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

export const Modal = styled.div`
  display: flex;
  flex-direction: column;
  border-radius: 20px;
  border: 3px solid #accebc;
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
  box-shadow: 0 0 10px #03e9f4, 0 0 20px #03e9f4;
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
