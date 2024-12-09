import { Box, Container, CssBaseline } from '@mui/material';
import { useState } from 'react';
import FileUpload from './components/FileUpload';
import TradeStats from './components/TradeStats';
import TradesTable from './components/TradesTable';
import './App.css';

function App() {
  //const [trades, setTrades] = useState([]); // to do: add this state
  
  //temporary testing state/fake data: 
  const [trades, setTrades] = useState([
    {
      id: 1,
      // add other relevant trade properties based on your data structure
      symbol: 'AAPL',
      quantity: 100,
      price: 150.50,
      date: '2023-01-01'
    }
  ]); 
  

  const handleDeleteTrade = (tradeId) => {
    // Filter out the deleted trade
    setTrades(trades.filter(trade => trade.id !== tradeId));
    
    // If you have a backend API, you would also want to make the delete request here
    // Example:
    // fetch(`/api/trades/${tradeId}`, {
    //   method: 'DELETE'
    // }).then(() => {
    //   setTrades(trades.filter(trade => trade.id !== tradeId));
    // }).catch(error => {
    //   console.error('Error deleting trade:', error);
    // });
  };

  return (
    <>
      <CssBaseline />
      <Container maxWidth="lg">
        <Box sx={{ my: 4 }}>
          <FileUpload />
          <TradeStats />
          <TradesTable 
            trades={trades} 
            onDeleteTrade={handleDeleteTrade} 
          />
        </Box>
      </Container>
    </>
  );
}

export default App;
