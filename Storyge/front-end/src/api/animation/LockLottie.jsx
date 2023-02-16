import { useLottie } from "lottie-react";
import book from "./../../assets/animation/book.json";

const style = {
  height: 300,
};

const LockLottie = () => {
  const options = {
    animationData: book,
    loop: true,
    autoplay: true,
  };

  const { View } = useLottie(options, style);

  return View;
};

export default LockLottie;
