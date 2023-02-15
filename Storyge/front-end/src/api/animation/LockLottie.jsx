import { useLottie } from "lottie-react";
import lock from "./../../assets/animation/lock2.json";

const style = {
  height: 300,
};

const LockLottie = () => {
  const options = {
    animationData: lock,
    loop: true,
    autoplay: true,
  };

  const { View } = useLottie(options, style);

  return View;
};

export default LockLottie;
