import { Box, Typography, Button, Container } from "@mui/material";
import { useNavigate } from "react-router-dom";

const HomePage = () => {
  const navigate = useNavigate();

  return (
    <Box
      sx={{
        minHeight: "100vh",
        display: "flex",
        flexDirection: "column",
        alignItems: "center",
        justifyContent: "center",
        bgcolor: "background.default",
      }}
    >
      <Container maxWidth="sm">
        <Typography
          variant="h2"
          component="h1"
          align="center"
          gutterBottom
          sx={{
            mb: 4,
            fontWeight: "bold",
            color: "primary.main",
          }}
        >
          Trade Viewer
        </Typography>

        <Box
          sx={{
            display: "flex",
            flexDirection: "column",
            gap: 2,
            alignItems: "center",
          }}
        >
          <Button
            variant="contained"
            size="large"
            fullWidth
            sx={{ width: "200px" }}
            onClick={() => navigate("/login")}
          >
            Login
          </Button>

          <Button
            variant="outlined"
            size="large"
            fullWidth
            sx={{ width: "200px" }}
            onClick={() => navigate("/register")}
          >
            Register
          </Button>

          <Button
            variant="text"
            size="large"
            fullWidth
            sx={{ width: "200px", mt: 2 }}
            onClick={() => navigate("/dashboard")}
          >
            View Demo
          </Button>
        </Box>
      </Container>
    </Box>
  );
};

export default HomePage;
