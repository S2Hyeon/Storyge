import React, { useState } from 'react';
import { useSpeechRecognition } from 'react-speech-kit';

export default function UseSpeech() {
  const [value, setValue] = useState('');
  const { listen, listening, stop } = useSpeechRecognition({
    onResult: (result) => {
      setValue(result);
    },
  });

  return (
    <div>
      <textarea
        value={value}
        onChange={(event) => setValue(event.target.value)}
      />
      <button onMouseDown={listen} onMouseUp={stop}>
        🎤
      </button>
      {listening && <div>Go ahead I'm listening</div>}
    </div>
  );
}