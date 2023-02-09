import React from "react";
import * as G from "./../../styles/index";
import * as S from "./../8_music/Music.js";
import ReactPlayer from "react-player";

export default function MusicResult(path) {
  return (
    <G.BodyContainer>
      {/* Youtube Player 자동실행 */}
      <ReactPlayer
        url={path}
        width="300px"
        height="200px"
        playing={true}
        style={{ margin: "auto" }}
      />
    </G.BodyContainer>
  );
}
