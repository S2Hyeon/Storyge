import React from 'react';
import ImageUploader from 'react-image-upload'
import 'react-image-upload/dist/index.css'

export default function ProfileBox() {
    function getImageFileObject(imageFile) {
        console.log({ imageFile })
      }
      function runAfterImageDelete(file) {
        console.log({ file })
      }
    return (
        <div className="ComponentBox">
            {/* 파일 삭제 버튼 수정 예정 */}
            <ImageUploader
                style={{ height: 150, width: 150, borderColor: 'gray', borderRadius: '50%'}}
                onFileAdded={(img) => getImageFileObject(img)}
                onFileRemoved={(img) => runAfterImageDelete(img)}
            />
        </div>
    );
}

