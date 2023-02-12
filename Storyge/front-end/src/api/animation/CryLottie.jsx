import { useLottie } from 'lottie-react'
import cry from './../../assets/animation/cry.json'

const style = {
  height: 300,
}

const CryLottie = () => {
  const options = {
    animationData: cry,
    loop: true,
    autoplay: true,
  }

  const { View } = useLottie(options, style)

  return View
}

export default CryLottie
