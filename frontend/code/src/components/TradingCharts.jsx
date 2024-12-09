import React from "react";
import {
  LineChart,
  Line,
  BarChart,
  Bar,
  PieChart,
  Pie,
  Cell,
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
  Legend,
  ResponsiveContainer,
} from "recharts";
import { Paper, Typography, Grid, Box } from "@mui/material";

const TradingCharts = ({ trades }) => {
  // Calculate cumulative P/L data for line chart
  const cumulativePL =
    trades?.reduce((acc, trade, index) => {
      const profit = acc[index - 1]?.cumulative || 0;
      return [
        ...acc,
        {
          date: trade.date,
          cumulative: profit + trade.profitLoss,
        },
      ];
    }, []) || [];

  // Calculate win/loss ratio data for pie chart
  const winLossData = trades?.reduce(
    (acc, trade) => {
      return {
        wins: acc.wins + (trade.profitLoss > 0 ? 1 : 0),
        losses: acc.losses + (trade.profitLoss < 0 ? 1 : 0),
      };
    },
    { wins: 0, losses: 0 }
  ) || { wins: 0, losses: 0 };

  // Calculate profit by symbol for bar chart
  const profitBySymbol = trades?.reduce((acc, trade) => {
    acc[trade.symbol] = (acc[trade.symbol] || 0) + trade.profitLoss;
    return acc;
  }, {});

  const symbolData = Object.entries(profitBySymbol || {}).map(
    ([symbol, profit]) => ({
      symbol,
      profit,
    })
  );

  const COLORS = ["#4CAF50", "#f44336"];

  return (
    <Box sx={{ maxWidth: 1200, mx: "auto", mt: 4, p: 2 }}>
      <Grid container spacing={3}>
        {/* Cumulative P/L Over Time */}
        <Grid item xs={12} md={6}>
          <Paper sx={{ p: 2 }}>
            <Typography variant="h6" gutterBottom>
              Cumulative Profit/Loss Over Time
            </Typography>
            <Box sx={{ width: "100%", height: 300 }}>
              <ResponsiveContainer>
                <LineChart data={cumulativePL}>
                  <CartesianGrid strokeDasharray="3 3" />
                  <XAxis dataKey="date" />
                  <YAxis />
                  <Tooltip />
                  <Legend />
                  <Line
                    type="monotone"
                    dataKey="cumulative"
                    stroke="#2196F3"
                    name="P/L ($)"
                  />
                </LineChart>
              </ResponsiveContainer>
            </Box>
          </Paper>
        </Grid>

        {/* Win/Loss Ratio */}
        <Grid item xs={12} md={6}>
          <Paper sx={{ p: 2 }}>
            <Typography variant="h6" gutterBottom>
              Win/Loss Ratio
            </Typography>
            <Box sx={{ width: "100%", height: 300 }}>
              <ResponsiveContainer>
                <PieChart>
                  <Pie
                    data={[
                      { name: "Wins", value: winLossData.wins },
                      { name: "Losses", value: winLossData.losses },
                    ]}
                    cx="50%"
                    cy="50%"
                    innerRadius={60}
                    outerRadius={80}
                    fill="#8884d8"
                    paddingAngle={5}
                    dataKey="value"
                  >
                    {[winLossData.wins, winLossData.losses].map(
                      (entry, index) => (
                        <Cell
                          key={`cell-${index}`}
                          fill={COLORS[index % COLORS.length]}
                        />
                      )
                    )}
                  </Pie>
                  <Tooltip />
                  <Legend />
                </PieChart>
              </ResponsiveContainer>
            </Box>
          </Paper>
        </Grid>

        {/* Profit by Symbol */}
        <Grid item xs={12}>
          <Paper sx={{ p: 2 }}>
            <Typography variant="h6" gutterBottom>
              Profit/Loss by Symbol
            </Typography>
            <Box sx={{ width: "100%", height: 300 }}>
              <ResponsiveContainer>
                <BarChart data={symbolData}>
                  <CartesianGrid strokeDasharray="3 3" />
                  <XAxis dataKey="symbol" />
                  <YAxis />
                  <Tooltip />
                  <Legend />
                  <Bar dataKey="profit" fill="#3f51b5" name="P/L ($)" />
                </BarChart>
              </ResponsiveContainer>
            </Box>
          </Paper>
        </Grid>
      </Grid>
    </Box>
  );
};

export default TradingCharts;
