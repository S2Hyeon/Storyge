import React, { useState } from "react";
import * as G from "../../styles/index";
import * as S from "./Music.js";
import { OpenAI } from "../../openai/OpenAI";
// import MusicResult from "./MusicResult";
import axios from "axios";
// import Spinner from "../../components/spinner/Spinner";
import ReactPlayer from "react-player";
import Lottie from "../../api/animation/Lottie.jsx";
// import UseSpeech from "./UseSpeech.jsx"

// import { reject } from "q";
// import { resolve } from "path";

export default function Music() {
  const [url, setUrl] = useState(null);
  const [content, setContent] = useState("");
  const [youtubeOpen, setYoutubeOpen] = useState(false);
  const [btnToggle, setBtnToggle] = useState(0);
  // const [videoId, setVideoId] = useState();
  async function findMusic() {
    setBtnToggle(1);
    const title = await OpenAI({ input: content, type: 0 });
    const result = title + " lylics";
    axios({
      method: "get",
      url: "https://www.googleapis.com/youtube/v3/search?",
      params: {
        key: process.env.REACT_APP_YOUTUBE_API_KEY,
        part: "snippet",
        q: result,
      },
    })
      .then((res) => {
        setUrl(
          `https://www.youtube.com/watch?v=${res.data.items[0].id.videoId}`
        );
        setYoutubeOpen(true);
        setBtnToggle(2);
      })
      .catch((err) => {
        console.log(err);
      });
  }

  return (
    <G.BodyContainer>
      <S.Rectangle
        placeholder="음악 추천을 받고 싶은 사연을 작성해주세요."
        value={content}
        onChange={(e) => {
          setContent(e.target.value);
        }}
      />
      {btnToggle === 0 ? (
        <G.longBtnDefault onClick={findMusic} style={{ marginBottom: "20px" }}>
          <S.Text>분석하기</S.Text>
        </G.longBtnDefault>
      ) : btnToggle === 1 ? (
        <G.longBtnDisabled style={{ marginBottom: "20px" }}>
          <S.Text>분석중...</S.Text>
        </G.longBtnDisabled>
      ) : null}
      {!youtubeOpen ? (
        <div>
          <Lottie />
        </div>
      ) : (
        <G.BodyContainer>
          {/* Youtube Player 자동실행 */}
          <ReactPlayer
            url={url}
            width="300px"
            height="200px"
            playing={true}
            style={{ margin: "auto" }}
          />
        </G.BodyContainer>
      )}
    </G.BodyContainer>
  );
}
