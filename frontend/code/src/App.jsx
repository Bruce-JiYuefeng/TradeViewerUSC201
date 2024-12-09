import { Box, Container, CssBaseline } from '@mui/material';
import FileUpload from './components/FileUpload';
import TradeStats from './components/TradeStats';
import TradesTable from './components/TradesTable';
import './App.css';

function App() {
  return (
    <>
      <CssBaseline />
      <Container maxWidth="lg">
        <Box sx={{ my: 4 }}>``
          <FileUpload />
          <TradeStats />
          <TradesTable />
        </Box>
      </Container>
    </>
  );
}

export default App;
