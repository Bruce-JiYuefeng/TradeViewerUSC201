import { 
  Table, 
  TableBody, 
  TableCell, 
  TableContainer, 
  TableHead, 
  TableRow, 
  Paper,
  IconButton 
} from '@mui/material';
import DeleteIcon from '@mui/icons-material/Delete';

function TradesTable({ trades = [
  // Sample trade for testing
  {
    id: 1,
    date: '2023-12-01',
    symbol: 'AAPL',
    quantity: 100,
    price: 189.95,
    type: 'BUY'
  }
], onDeleteTrade }) {
  const handleDelete = (tradeId) => {
    // Call the parent component's delete handler
    onDeleteTrade(tradeId);
  };

  return (
    <TableContainer component={Paper}>
      <Table>
        <TableHead>
          <TableRow>
            <TableCell>Date</TableCell>
            <TableCell>Symbol</TableCell>
            <TableCell>Quantity</TableCell>
            <TableCell>Price</TableCell>
            <TableCell>Type</TableCell>
            <TableCell>Actions</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {trades.map((trade) => (
            <TableRow key={trade.id}>
              <TableCell>{trade.date}</TableCell>
              <TableCell>{trade.symbol}</TableCell>
              <TableCell>{trade.quantity}</TableCell>
              <TableCell>{trade.price}</TableCell>
              <TableCell>{trade.type}</TableCell>
              <TableCell>
                <IconButton 
                  aria-label="delete" 
                  onClick={() => handleDelete(trade.id)}
                  color="error"
                >
                  <DeleteIcon />
                </IconButton>
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
}

export default TradesTable;
