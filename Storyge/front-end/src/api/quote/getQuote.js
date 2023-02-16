// import axios from "axios";
import Api from "lib/customApi";

export async function getQuote() {
  try {
    const response = await Api.get("/quote");
    return response.data;
  } catch (error) {
    console.error(error);
  }
}
