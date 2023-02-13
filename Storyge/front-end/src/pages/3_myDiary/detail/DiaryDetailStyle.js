import styled from "styled-components";

export const submitBtn = styled.button`
  background-color: var(--color-primary);
  border-radius: 100px;
  border: hidden;
  width: 80px;
  height: 35px;
  font-size: 16px;
`;

export const DiaryContainer = styled.div`
  border-radius: 20px 20px 0 0;
  border: 3px solid #accebc;
  margin: 20px 0 0 0;
  width: 100%;
  background-color: var(--color-background);
  // box-sizing: border-box;
`;

export const Diary = styled.div`
  margin: 10px;
  display: flex;
  min-height: 160px;
  // align-items: center;
`;

export const EmotionContainer = styled.div`
  // border: 1px solid blue;
  width: 62px;
  display: flex;
  align-items: center;
  justify-content: center;
  margin: 0 10px 0 0;
`;

export const Emotion = styled.div`
  // border: 1px solid red;

  height: 62px;
  width: 62px;
  background-image: url("https://t4.daumcdn.net/thumb/R720x0.fjpg/?fname=http://t1.daumcdn.net/brunch/service/user/2hnC/image/yq60UzE-_opOB3-DslxCS1ea3Bk.jpg");
  background-size: cover;
  border-radius: 100px;
  flex: 0 0 auto;
`;

export const ContentContiner = styled.div`
  // border: 1px solid green;
  height: 100%;
  width: 100%;
`;

export const ProfileImg = styled.div`
  height: 40px;
  width: 40px;
  background-image: url(${(props) => props.emotion});
  background-size: cover;
  border-radius: 100px;
  flex: 0 0 auto;
  margin: 0 10px 0 0;
`;

export const TimeContainer = styled.div`
  // border: 1px solid black;
  width: 100%;
  height: 26px;
  display: flex;
  font-family: "S-CoreDream-5Medium";
  font-size: 15px;
`;

export const Content = styled.div`
  // border: 1px solid pink;
  width: 100%;
  font-family: "S-CoreDream-3Light";
  font-size: 15px;
  overflow: auto;
`;

export const AnalyzedContainer = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  border: 3px solid #accebc;
  background-color: var(--color-primary);
  min-height: ${(props) => props.height};
  border-radius: 0 0 20px 20px;
`;

export const Toggle = styled.div`
  border: hidden;
  font-size: ${(props) => props.fontSize};
  border-radius: 100px;
  height: 30px;
  margin: 0 10px;
`;

export const ToggleBtnBox = styled.div`
  // border: 1px solid red;
  width: 100%;
  display: flex;
  justify-content: right;
`;

export const Analyzed = styled.div`
  // border: 1px solid blue;
  margin: 0 10px 10px 10px;
`;

// 댓글 관련 요소
export const CommentWriteBox = styled.div`
  // border: 1px solid red;
  border: hidden;
  background-color: var(--color-grey);
  border-radius: 100px;
  height: 35px;
  width: 100%;
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

export const CommentBox = styled.div`
  border-color: white white var(--color-primary);
  border-style: solid;
  border-width: 1px;
  min-height: 80px;
  top: 50%;
  width: 100%;
  display: flex;
  flex-direction: column;
  // align-items: center;
  text-align: left;
`;

export const CommentInfo = styled.div`
  // border: 1px solid red;
  width: 100%;
  display: flex;
  align-items: center;
  margin: 5px 0;
`;

export const CommentNameTime = styled.div`
  // border: 1px solid blue;
  font-size: 14px;
  margin-right: 43%;
`;

export const CommentName = styled.div`
  // border: 1px solid green;
  font-family: "S-CoreDream-5Medium";
`;

export const CommentTime = styled.div`
  // border: 1px solid red;
  font-family: "S-CoreDream-3Light";
  color: #afafaf;
`;

export const CommentContent = styled.div`
  // border: 1px solid purple;
  margin: 0 0 5px 0;
`;

export const Row = styled.div`
  display: flex;
  justify-content: flex-end;
  align-items: center;
  margin: 10px 0 0 0;
`;

export const InfoBtn = styled.button`
  background-color: ${(props) => props.color};
  border-radius: 100px;
  border: hidden;
  width: 80px;
  height: 35px;
  font-size: 16px;
  margin: 0 5px 0 5px;
`;

export const Test = styled.div`
  margin: 0 0 0 7px;
`;
