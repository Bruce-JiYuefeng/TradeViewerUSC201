import { DataGrid } from '@mui/x-data-grid';
import { Box, Typography } from '@mui/material';

function TradesTable({ trades }) {
  const columns = [
    { field: 'date', headerName: 'Date', width: 130 },
    { field: 'symbol', headerName: 'Symbol', width: 130 },
    { field: 'type', headerName: 'Type', width: 90 },
    { field: 'quantity', headerName: 'Quantity', width: 100, type: 'number' },
    { field: 'entryPrice', headerName: 'Entry Price', width: 130, type: 'number' },
    { field: 'exitPrice', headerName: 'Exit Price', width: 130, type: 'number' },
    {
      field: 'profitLoss',
      headerName: 'Profit/Loss',
      width: 130,
      type: 'number',
      valueFormatter: (params) => {
        if (params.value == null) return '';
        return `$${params.value.toLocaleString()}`;
      },
    },
  ];

  // Demo data
  const rows = [
    { 
      id: 1, 
      date: '2023-01-01', 
      symbol: 'AAPL', 
      type: 'Buy', 
      quantity: 100,
      entryPrice: 150.00,
      exitPrice: 155.00,
      profitLoss: 500
    },
    // Add more demo rows as needed
  ];

  return (
    <Box sx={{ height: 400, width: '100%', mt: 4 }}>
      <Typography variant="h4" gutterBottom>
        Trade History
      </Typography>
      <DataGrid
        rows={trades || rows}
        columns={columns}
        pageSize={5}
        rowsPerPageOptions={[5]}
        checkboxSelection
        disableSelectionOnClick
      />
    </Box>
  );
}

export default TradesTable;
