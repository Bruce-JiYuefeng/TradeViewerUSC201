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
    const fetchTrades = () => {
      const userId = localStorage.getItem("userId"); // Retrieve userId from localStorage
      if (!userId) {
        console.error("User ID not found");
        return;
      }

      axios.get('/api/refresh-trades', {
        headers: { 'userId': userId }
      })
      .then(response => {
        setTrades(response.data);
      })
      .catch(error => {
        console.error("There was an error fetching the trades data!", error);
      });
    };

    fetchTrades(); // Initial fetch
    const intervalId = setInterval(fetchTrades, 3000); // Fetch every 60 seconds

    return () => clearInterval(intervalId); // Cleanup on component unmount
  }, []);

  const handleDeleteTrade = (tradeId) => {
    // Send DELETE request with JSON payload
    axios.delete('/api/tradesDelete', {
      data: { tradeId: tradeId, state: "delete" }
    })
    .then(() => {
      // Update the state to remove the trade locally
      setTrades(trades.filter((trade) => trade.id !== tradeId));
    })
    .catch(error => {
      console.error("There was an error deleting the trade!", error);
    });
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
