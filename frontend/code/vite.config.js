import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

export default defineConfig({
  plugins: [react()],
  server: {
    proxy: {
      '/api': {
        target: 'http://localhost:8083/20124fallFP', // Backend base URL, change 8083 to whatever port number of your tomcat server
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api/, ''), // Strip '/api' prefix
      },
    },
  },
});
