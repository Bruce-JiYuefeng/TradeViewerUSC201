import { Box, Paper, Typography, Grid } from '@mui/material';

function TradeStats({ stats }) {
  // This is a placeholder for actual stats
  const demoStats = {
    totalTrades: 100,
    winningTrades: 65,
    totalProfit: 5000,
    winPercentage: 65,
    averageProfit: 50,
  };

  return (
    <Box sx={{ maxWidth: 1200, mx: 'auto', mt: 4, p: 2 }}>
      <Typography variant="h4" gutterBottom>
        Trading Statistics
      </Typography>
      
      <Grid container spacing={3}>
        <Grid item xs={12} sm={6} md={4}>
          <Paper sx={{ p: 2 }}>
            <Typography variant="h6">Total Trades</Typography>
            <Typography variant="h4">{demoStats.totalTrades}</Typography>
          </Paper>
        </Grid>
        
        <Grid item xs={12} sm={6} md={4}>
          <Paper sx={{ p: 2 }}>
            <Typography variant="h6">Win Percentage</Typography>
            <Typography variant="h4">{demoStats.winPercentage}%</Typography>
          </Paper>
        </Grid>
        
        <Grid item xs={12} sm={6} md={4}>
          <Paper sx={{ p: 2 }}>
            <Typography variant="h6">Total Profit/Loss</Typography>
            <Typography variant="h4">${demoStats.totalProfit}</Typography>
          </Paper>
        </Grid>
      </Grid>
    </Box>
  );
}

export default TradeStats;