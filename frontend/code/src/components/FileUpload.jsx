import { useState } from 'react';
import { Button, Box, Typography, Alert } from '@mui/material';
import CloudUploadIcon from '@mui/icons-material/CloudUpload';

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

    try {
      // TODO: Replace with your actual API endpoint
      // const response = await axios.post('/api/upload', formData);
      console.log('File uploaded successfully');
    } catch (error) {
      setError('Error uploading file');
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
        <label htmlFor="raised-button-file">
          <Button
            variant="contained"
            component="span"
            startIcon={<CloudUploadIcon />}
            sx={{ mb: 2 }}
          >
            Select CSV File
          </Button>
        </label>
        
        {file && (
          <Typography variant="body1" sx={{ mb: 2 }}>
            Selected file: {file.name}
          </Typography>
        )}
        
        {error && (
          <Alert severity="error" sx={{ mb: 2 }}>
            {error}
          </Alert>
        )}
        
        <Button
          variant="contained"
          color="primary"
          type="submit"
          disabled={!file}
        >
          Upload
        </Button>
      </Box>
    </Box>
  );
}

export default FileUpload;