import {createSlice, createAsyncThunk} from "@reduxjs/toolkit";
import axios from "axios";

export const initialState = {
  signUpLoading: false,
  signUpDone: false,
  signUpError: null,
  signInLoading: false,
  signInDone: false,
  signInError: null,
  oauth2SignInLoading: false,
  oauth2SignInDone: false,
  oauth2SignInError: null,
  me: null,
};

export const signUpAsync = createAsyncThunk(
  'user/SIGN_UP',
  async (data, thunkAPI) => {
    try {
      const response = await axios.post(
        '/users/new',
        data
      );
 
      return response.data
    } catch (err) {
      return thunkAPI.rejectWithValue(err);
    }
  }
);

export const signInAsync = createAsyncThunk(
  'user/SIGN_IN',
  async (data, thunkAPI) => {
    try {
      const response = await axios.post(
        '/users/auth',
        data
      );
      return response.data
    } catch (err) {
      return thunkAPI.rejectWithValue(err);
    }
  }
);

export const oauth2SignInAsync = createAsyncThunk(
  'user/OAUTH_SIGN_IN',
  async (data, thunkAPI) => {
    try {
      return data
    } catch (err) {
      return thunkAPI.rejectWithValue(err);
    }
  }
);

const userSlice = createSlice({
  name: "user",
  initialState,
  reducers: {
  
  },
  extraReducers: (builder) => {
    builder.addCase(signUpAsync.pending, (state, action) => {
      state.signUpLoading = true;
      state.signUpError = null;
      state.signUpDone = false;
    });
    builder.addCase(signUpAsync.fulfilled, (state, action) => {
      state.signUpLoading = false;
      state.signUpDone = true;
      alert('회원가입에 성공하였습니다.');
    });
    builder.addCase(signUpAsync.rejected, (state, action) => {
      state.signUpLoading = false;
      state.signUpError = action.data
      alert('회원가입에 실패했습니다.');
    });
    builder.addCase(signInAsync.pending, (state, action) => {
      state.signInLoading = true;
      state.signInError = null;
      state.signInDone = false;
    });
    builder.addCase(signInAsync.fulfilled, (state, action) => {
      state.signInLoading = false;
      state.me = {
        email : action.payload.email,
        nickname: action.payload.nickname
      }
      sessionStorage.clear()
      sessionStorage.setItem("access-token", action.payload.accesstoken)
      sessionStorage.setItem("refresh-token", action.payload.refreshtoken)
      alert('로그인에 성공하였습니다.');
      state.signInDone = true;
    });
    builder.addCase(signInAsync.rejected, (state, action) => {
      state.signInLoading = false;
      state.signInError = action.error
      alert('로그인에 실패했습니다.');
    });
    builder.addCase(oauth2SignInAsync.pending, (state, action) => {
      state.signInLoading = true;
      state.signInError = null;
      state.signInDone = false;
    });
    builder.addCase(oauth2SignInAsync.fulfilled, (state, action) => {
      state.signInLoading = false;
      state.me = {
        email : action.payload.email,
        nickname: action.payload.nickname
      }
      sessionStorage.clear()
      sessionStorage.setItem("access-token", action.payload.accesstoken)
      sessionStorage.setItem("refresh-token", action.payload.refreshtoken)
      state.signInDone = true;
    });
    builder.addCase(oauth2SignInAsync.rejected, (state, action) => {
      state.signInLoading = false;
      state.signInError = action.payload
      alert('로그인에 실패했습니다.');
    });
  }
});

export default userSlice.reducer;