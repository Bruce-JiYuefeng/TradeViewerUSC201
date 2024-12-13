import { useState } from 'react';
import { Button, Box, Typography, Alert, Stack } from '@mui/material';
import CloudUploadIcon from '@mui/icons-material/CloudUpload';
import axios from 'axios';

function FileUpload() {
  const [file, setFile] = useState(null);
  const [error, setError] = useState('');

  const handleFileUpload = (event) => {
    const selectedFile = event.target.files[0];
    
    if (selectedFile && selectedFile.type === 'text/csv') {
      setFile(selectedFile);
      setError('');
    } else {
      setFile(null);
      setError('Please upload a valid CSV file');
    }
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    if (!file) {
      setError('Please select a file first');
      return;
    }

    const formData = new FormData();
    formData.append('file', file);
    
    const userId = localStorage.getItem("userId");
    if (!userId) {
      setError('User not logged in');
      return;
    }

    try {
      const response = await axios.post('/api/upload-csv', formData, {
        headers: {
          'userId': userId
        }
      });

      if (response.status === 200) {
        console.log('File uploaded successfully');
      } else {
        throw new Error('Failed to upload file');
      }
    } catch (error) {
      setError(`Error uploading file: ${error.response?.data?.message || error.message}`);
      console.error('Error:', error);
    }
  };

  return (
    <Box sx={{ maxWidth: 600, mx: 'auto', mt: 4, p: 2 }}>
      <Typography variant="h4" gutterBottom>
        Upload Trading Data
      </Typography>
      
      <Box component="form" onSubmit={handleSubmit}>
        <input
          accept=".csv"
          style={{ display: 'none' }}
          id="raised-button-file"
          type="file"
          onChange={handleFileUpload}
        />
        
        <Stack direction="row" spacing={2} alignItems="center" justifyContent="center">
          <label htmlFor="raised-button-file">
            <Button
              variant="contained"
              component="span"
              startIcon={<CloudUploadIcon />}
            >
              Select CSV File
            </Button>
          </label>
          
          <Button
            variant="contained"
            color="primary"
            type="submit"
            disabled={!file}
          >
            Upload
          </Button>
        </Stack>
        
        {file && (
          <Typography variant="body1" sx={{ mt: 2 }}>
            Selected file: {file.name}
          </Typography>
        )}
        
        {error && (
          <Alert severity="error" sx={{ mt: 2 }}>
            {error}
          </Alert>
        )}
      </Box>
    </Box>
  );
}

export default FileUpload;
