import React, { useState } from 'react';
import { Card, CardContent } from "@/components/ui/card";
import { Input } from "@/components/ui/input";
import { Button } from "@/components/ui/button";
import { Alert, AlertDescription } from "@/components/ui/alert";

const LoginPage = () => {
  const [formData, setFormData] = useState({
    username: '',
    password: ''
  });
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData(prev => ({
      ...prev,
      [name]: value
    }));
    // clear the error when user starts typing
    if (error) setError('');
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    
    // validation of fields
    if (!formData.username || !formData.password) {
      setError('Please fill in all fields');
      return;
    }

    try {
      setLoading(true);
      setError('');

      // example of api call we will make
      const response = await fetch('/api/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(formData)
      });

      if (!response.ok) {
        throw new Error('Login failed');
      }

      const data = await response.json();
      
      // Handle successful login
      // store token in local storage
      if (data.token) {
        localStorage.setItem('token', data.token);
      }
      
      // update context state
      // setAuth({ user: data.user, token: data.token });
      
      // redirect to the dashboard (not built out yet)
      // window.location.href = '/dashboard';
      
    } 
    catch (err) {
      setError(err.message || 'Invalid username or password');
    } 
    finally {
      setLoading(false);
    }
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-900">
      <Card className="w-80 bg-gray-800 text-white">
        <CardContent className="pt-6">
          <h1 className="text-2xl font-bold mb-6">Login</h1>
          
          {error && (
            <Alert variant="destructive" className="mb-4 bg-red-900 border-red-800">
              <AlertDescription>{error}</AlertDescription>
            </Alert>
          )}
          
          <form onSubmit={handleSubmit} className="space-y-4">
            <div className="space-y-2">
              <label className="block text-gray-300">Username:</label>
              <Input 
                type="text"
                name="username"
                value={formData.username}
                onChange={handleChange}
                className="w-full bg-gray-700 border-gray-600 text-white"
                disabled={loading}
              />
            </div>
            
            <div className="space-y-2">
              <label className="block text-gray-300">Password:</label>
              <Input 
                type="password"
                name="password"
                value={formData.password}
                onChange={handleChange}
                className="w-full bg-gray-700 border-gray-600 text-white"
                disabled={loading}
              />
            </div>

            <Button 
              type="submit"
              className="w-full bg-green-600 hover:bg-green-700 text-white"
              disabled={loading}
            >
              {loading ? 'Logging in...' : 'Login'}
            </Button>
          </form>
        </CardContent>
      </Card>
    </div>
  );
};

export default LoginPage;