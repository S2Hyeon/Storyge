import styled from "styled-components";

export const DateContainer = styled.div`
  display: flex;
  font-size: 18px;
  width: 100%;
  justify-content: center;
  align-items: center;
  height: 60px;
  border-color: white white var(--color-primary);
  border-style: solid;
  border-width: 0.5px;
  text-align: center;
`;

export const DateInfo = styled.div`
  width: 80%;
`;

export const ArrowBtn = styled.div`
  width: 10%;
`;

export const NoDiaryList = styled.div`
  width: 100%;
  height: 300px;
  display: flex;
  justify-content: center;
  align-items: center;
  font-family: "S-CoreDream-6Bold";
`;

//리스트 요소
export const ListBox = styled.div`
  border-color: transparent transparent var(--color-primary);
  border-style: solid;
  border-width: 0.5px;
  height: 80px;
  width: 100%;
  display: flex;
  align-items: center;
  text-align: left;
`;

export const Emotion = styled.div`
  width: 53px;
  height: 53px;
  background-image: url(${(props) => props.emotion});
  border-radius: 100px;
  background-size: cover;
  margin: 0 5px;
`;

export const TimeSummaryContainer = styled.div`
  margin: 5px 5px 5px 12px;
  width: 80%;
`;

export const Time = styled.div`
  height: 20%;
  font-size: 14px;
  font-family: "S-CoreDream-4Regular";
`;

export const Summary = styled.div`
  font-size: 12px;

  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  word-wrap: break-word;
  line-height: 1.2em;
  height: 2.4em;
`;
