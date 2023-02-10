import React from 'react';
import ImageUploader from 'react-image-upload'
import 'react-image-upload/dist/index.css'
import { AiTwotoneCamera } from "react-icons/ai";
import * as S from "./../profileBox/ProfileImgBoxStyle";

export default function ProfileBoxImg(props) {
    function getImageFileObject(imageFile) {
        console.log({ imageFile })
      }
      function runAfterImageDelete(file) {
        console.log({ file })
      }
    return (
        <>
            <S.Box >
                <S.Img src={props.profileImg} />

                <S.Btn>
                    <AiTwotoneCamera />
                </S.Btn>
            </S.Box>
        </>
    );
}

