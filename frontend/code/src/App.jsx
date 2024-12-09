import { Routes, Route, Link } from "react-router-dom";
import { Box, Container, CssBaseline, Button } from "@mui/material";
import FileUpload from "./components/FileUpload";
import TradeStats from "./components/TradeStats";
import TradesTable from "./components/TradesTable";
import TradingCharts from "./components/TradingCharts";
import LoginPage from "./Login";
import "./App.css";

function Dashboard() {
  const sampleTrades = [
    {
      id: 1,
      date: "2024-01-01",
      symbol: "AAPL",
      type: "Buy",
      quantity: 100,
      entryPrice: 150.0,
      exitPrice: 155.0,
      profitLoss: 500,
    },
    {
      id: 2,
      date: "2024-01-15",
      symbol: "GOOGL",
      type: "Buy",
      quantity: 50,
      entryPrice: 2800.0,
      exitPrice: 2750.0,
      profitLoss: -2500,
    },
    {
      id: 3,
      date: "2024-02-01",
      symbol: "TSLA",
      type: "Sell",
      quantity: 75,
      entryPrice: 200.0,
      exitPrice: 180.0,
      profitLoss: 1500,
    },
    {
      id: 4,
      date: "2024-02-15",
      symbol: "AAPL",
      type: "Buy",
      quantity: 150,
      entryPrice: 160.0,
      exitPrice: 165.0,
      profitLoss: 750,
    },
    {
      id: 5,
      date: "2024-03-01",
      symbol: "MSFT",
      type: "Buy",
      quantity: 80,
      entryPrice: 300.0,
      exitPrice: 310.0,
      profitLoss: 800,
    },
  ];

  return (
    <Container maxWidth="lg">
      <Box sx={{ my: 4 }}>
        <Box sx={{ display: "flex", justifyContent: "flex-end", mb: 2 }}>
          <Link to="/login" style={{ textDecoration: "none" }}>
            <Button variant="contained" color="primary">
              Login
            </Button>
          </Link>
        </Box>
        <FileUpload />
        <TradeStats />
        <TradesTable trades={sampleTrades} />
        <TradingCharts trades={sampleTrades} />
      </Box>
    </Container>
  );
}

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
      <Routes>
        <Route path="/" element={<Dashboard />} />
        <Route path="/login" element={<LoginPage />} />
      </Routes>
    </>
  );
}

export default App;
