import { useState, useEffect } from "react";
import axios from "axios"; // Import axios
import { Routes, Route, Link } from "react-router-dom";
import { Box, Container, CssBaseline, Button } from "@mui/material";
import FileUpload from "./components/FileUpload";
import TradeStats from "./components/TradeStats";
import TradesTable from "./components/TradesTable";
import TradingCharts from "./components/TradingCharts";
import LoginPage from "./Login";
import Registration from "./Register";
import HomePage from "./HomePage";
import DemoPage from "./DemoPage";
import "./App.css";

function Dashboard() {
  const [trades, setTrades] = useState([]);

  useEffect(() => {
    // Fetch trades data from the backend
    axios.get('/api//upload-csv')
      .then(response => {
        setTrades(response.data);
      })
      .catch(error => {
        console.error("There was an error fetching the trades data!", error);
      });
  }, []);

  const handleDeleteTrade = (tradeId) => {
    setTrades(trades.filter((trade) => trade.id !== tradeId));
  };

  return (
    <>
      <Box sx={{ position: "absolute", top: 20, left: 20 }}>
        <Button component={Link} to="/" variant="contained">
          Home
        </Button>
      </Box>
      <Container maxWidth="lg">
        <Box sx={{ my: 4 }}>
          <FileUpload />
          <TradeStats trades={trades} />
          <TradesTable trades={trades} onDeleteTrade={handleDeleteTrade} />
          <TradingCharts trades={trades} />
        </Box>
      </Container>
    </>
  );
}

function App() {
  return (
    <>
      <CssBaseline />
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/login" element={<LoginPage />} />
        <Route path="/Register" element={<Registration />} />
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="/demo" element={<DemoPage />} />
      </Routes>
    </>
  );
}

export default App;
