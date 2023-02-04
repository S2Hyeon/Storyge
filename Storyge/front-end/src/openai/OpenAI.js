import { Configuration, OpenAIApi } from "openai";

export const OpenAI = async ({ input, type }) => {
  let result = "";

  const configuration = new Configuration({
    // apiKey: process.env.REACT_APP_OPENAI_API_KEY,
    apiKey: "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",
  });
  const openai = new OpenAIApi(configuration);

  const arrayItems = [
    {
      name: "Music recommend",
      id: "music",
      option: {
        model: "text-davinci-003",
        prompt: `"see this sentense, and recommend me appropriate song.\n\nTweet: "${input}"`,
        temperature: 0,
        max_tokens: 100,
        top_p: 1,
        frequency_penalty: 0,
        presence_penalty: 0,
      },
    },
    {
      name: "Englilsh to Korea",
      id: "translate",
      option: {
        model: "text-davinci-003",
        prompt: `Translate this into Korean:\n\n${input}\n\n`,
        temperature: 0.3,
        max_tokens: 100,
        top_p: 1,
        frequency_penalty: 0,
        presence_penalty: 0,
      },
    },
    {
      name: "Tweet classifier",
      id: "classify",
      option: {
        model: "text-davinci-003",
        prompt: `Decide whether a Tweet's sentiment is angry, aversion, happy, sad, scared, soso, or surprised.\n\nTweet: ${input}\nSentiment:`,
        temperature: 0,
        max_tokens: 60,
        top_p: 1,
        frequency_penalty: 0.5,
        presence_penalty: 0,
      },
    },
  ];

  const option = arrayItems[type].option;

  const response = await openai.createCompletion(option);
  result = response.data.choices[0].text;
  console.log(result);
  return result;
};
