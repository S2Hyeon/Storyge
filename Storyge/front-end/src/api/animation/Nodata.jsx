import { useLottie } from "lottie-react";
import nodata from "./../../assets/animation/nodata.json";

const style = {
  height: 300,
};

const NodataLottie = () => {
  const options = {
    animationData: nodata,
    loop: true,
    autoplay: true,
  };

  const { View } = useLottie(options, style);

  return View;
};

export default NodataLottie;
