import { useLottie } from "lottie-react";
import musicwave from "./../../assets/animation/musicwavegreen.json";

const style = {
  height: 300,
};

const Person = () => {
  const options = {
    animationData: musicwave,
    loop: true,
    autoplay: true,
  };

  const { View } = useLottie(options, style);

  return View;
};

export default Person;