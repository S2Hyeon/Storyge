import React from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';

export default function Music() {
  return (
    <div>
      <h1>Music</h1>
      <Box
      component="form"
      sx={{
        '& .MuiTextField-root': { m: 1, width: '25ch' },
      }}
      noValidate
      autoComplete="off"
    >
      <TextField
          id="outlined-multiline-static"
          label="Multiline"
          multiline
          rows={10}
          defaultValue="사연을 작성해주세요."
        />
    </Box>
      


    </div>
  );
}

