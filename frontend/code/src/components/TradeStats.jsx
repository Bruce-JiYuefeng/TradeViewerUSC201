import { Box, Paper, Typography, Grid } from '@mui/material';

function TradeStats({ trades }) {
  // Calculate statistics based on trades
  const totalTrades = trades.length;
  const winningTrades = trades.filter(trade => trade.profitLoss > 0).length;
  const totalProfit = trades.reduce((acc, trade) => acc + trade.profitLoss, 0);
  const winPercentage = totalTrades > 0 ? (winningTrades / totalTrades) * 100 : 0;
  const averageProfit = totalTrades > 0 ? totalProfit / totalTrades : 0;

  return (
    <Box sx={{ maxWidth: 1200, mx: 'auto', mt: 4, p: 2 }}>
      <Typography variant="h4" gutterBottom>
        Trading Statistics
      </Typography>
      
      <Grid container spacing={3}>
        <Grid item xs={12} sm={6} md={4}>
          <Paper sx={{ p: 2 }}>
            <Typography variant="h6">Total Trades</Typography>
            <Typography variant="h4">{totalTrades}</Typography>
          </Paper>
        </Grid>
        
        <Grid item xs={12} sm={6} md={4}>
          <Paper sx={{ p: 2 }}>
            <Typography variant="h6">Win Percentage</Typography>
            <Typography variant="h4">{winPercentage.toFixed(2)}%</Typography>
          </Paper>
        </Grid>
        
        <Grid item xs={12} sm={6} md={4}>
          <Paper sx={{ p: 2 }}>
            <Typography variant="h6">Total Profit/Loss</Typography>
            <Typography variant="h4">${totalProfit.toFixed(2)}</Typography>
          </Paper>
        </Grid>
      </Grid>
    </Box>
  );
}

export default TradeStats;
