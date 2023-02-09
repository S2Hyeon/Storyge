import axios from "axios";

export async function getQuote() {
  try {
    const response = await axios.get("/quote");
    console.log("response: ", response.data);
    return response.data;
  } catch (error) {
    console.error(error);
  }
}
