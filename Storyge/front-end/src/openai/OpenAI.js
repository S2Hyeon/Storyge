import { Configuration, OpenAIApi } from "openai";

export const OpenAI = async ({ input, type }) => {
  let result = "";
  const emotionList = [
    "angry",
    "aversion",
    "happy",
    "sad",
    "scared",
    "soso",
    "surprised",
  ];

  const configuration = new Configuration({
    apiKey: process.env.REACT_APP_OPENAI_API_KEY,
  });
  const openai = new OpenAIApi(configuration);
  const arrayItems = [
    {
      name: "Music recommend",
      id: "music",
      option: {
        model: "text-davinci-003",
        prompt: `see this sentense, and recommend me appropriate song.\n\nTweet: ${input}`,
        temperature: 0,
        max_tokens: 100,
        top_p: 1,
      },
    },
    {
      name: "Tweet classifier",
      id: "classify",
      option: {
        model: "text-davinci-003",
        // Decide whether a Tweet's sentiment is angry, aversion, happy, sad, scared, soso, or surprised.
        prompt: `Decide whether a Tweet's sentiment is angry, aversion, happy, sad, scared, soso, or surprised. and show me why you think like that\n\nTweet: ${input}\nSentiment:`,
        temperature: 0,
        max_tokens: 60,
        top_p: 1,
        frequency_penalty: 0.5,
        presence_penalty: 0,
      },
    },
    {
      name: "Englilsh to Korea",
      id: "translate",
      option: {
        model: "text-davinci-003",
        prompt: `Translate this into Korean:${input}`,
        temperature: 0.3,
        max_tokens: 250,
        top_p: 1,
        frequency_penalty: 0,
        presence_penalty: 0,
      },
    },
  ];
  const option = arrayItems[type].option;

  const response = await openai.createCompletion(option);
  // type 0 : music, 1 : classify, 2 : translate
  result = response.data.choices[0].text;
  if (type === 0) {
    let start, end;
    let trigger = 0;
    for (let i = 0; i < result.length; i++) {
      if (result[i] === '"') {
        if (trigger === 0) {
          start = i;
          trigger += 1;
        } else {
          end = i;
          break;
        }
      }
    }
    let music = result.substring(start + 1, end);
    return music;
  } else if (type === 1) {
    let start;
    for (let i = 0; i < result.length; i++) {
      if (result[i] === ":") {
        start = i;
        break;
      }
    }
    // comment refine
    let b = result.substring(start, result.length);

    // emotion refine
    let c = result.substring(0, start);
    let d = c.split("\n\nReason");
    let e = d[0].trim();

    // result return
    let emotion = e.toLowerCase();
    const comment = b.split(": ")[1];
    emotionList.map((target) => {
      if (emotion.includes(target)) {
        emotion = target;
      }
    });

    return [emotion, comment];
  } else {
    let comment_kr;
    if (input === undefined) {
      comment_kr =
        "일기가 감정을 분석하기에 충분하지 않습니다. 분석을 원하시면 수정을 통해 조금더 자세히 써봐요.";
    } else {
      comment_kr = result;
    }
    return comment_kr;
  }
};
