import { useState } from "react"; // Add this import
import { Routes, Route, Link } from "react-router-dom";
import { Box, Container, CssBaseline, Button } from "@mui/material";
import FileUpload from "./components/FileUpload";
import TradeStats from "./components/TradeStats";
import TradesTable from "./components/TradesTable";
import TradingCharts from "./components/TradingCharts";
import LoginPage from "./Login";
import HomePage from "./HomePage";
import "./App.css";

function Dashboard() {
  // Initialize trades state with the sample data
  const [trades, setTrades] = useState([
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
  ]);

  const handleDeleteTrade = (tradeId) => {
    setTrades(trades.filter(trade => trade.id !== tradeId));
  };

  return (
    <Container maxWidth="lg">
      <Box sx={{ my: 4 }}>
        <FileUpload />
        <TradeStats />
        <TradesTable trades={trades} onDeleteTrade={handleDeleteTrade}/>
        <TradingCharts trades={trades} />
      </Box>
    </Container>
  );
}

function App() {
  return (
    <>
      <CssBaseline />
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/login" element={<LoginPage />} />
        <Route path="/dashboard" element={<Dashboard />} />
      </Routes>
    </>
  );
}

export default App;
