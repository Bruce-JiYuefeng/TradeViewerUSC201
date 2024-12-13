import React, { useState } from "react";
import { Card, CardContent } from "@/components/ui/card";
import { Button } from "@/components/ui/button";
import { Alert, AlertDescription } from "@/components/ui/alert";
import { useNavigate } from "react-router-dom";

const FileUpload = () => {
  const [selectedFile, setSelectedFile] = useState(null);
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  const validateCSV = (file) => {
    // check if user inputted csv file
    if (!file.name.endsWith(".csv")) {
      throw new Error("Please upload a CSV file");
    }

    // file size
    if (file.size > 5 * 1024 * 1024) {
      throw new Error("File size should be less than 5MB");
    }

    return true;
  };

  const handleFileSelect = (e) => {
    const file = e.target.files[0];
    setError("");

    if (!file) return;

    try {
      validateCSV(file);
      setSelectedFile(file);
    } catch (err) {
      setError(err.message);
      setSelectedFile(null);
      e.target.value = ""; // reset the input of file
    }
  };

  const handleUpload = async (e) => {
    e.preventDefault();

    if (!selectedFile) {
      setError("Please select a file");
      return;
    }

    try {
      setLoading(true);
      setError("");

      // Create FormData for file upload
      const formData = new FormData();
      formData.append("file", selectedFile);

      const userId = localStorage.getItem("userId");
      if (!userId) {
        setError('User not logged in');
        return;
      }

      const response = await fetch("/api/upload-csv", {
        method: "POST",
        body: formData,
        headers: {
          'userId': userId
        }
      });

      if (!response.ok) {
        const errorData = await response.json();
        throw new Error(errorData.message || "Failed to upload file");
      }

      const data = await response.json();

      // If validation on server side passes
      if (data.isValid) {
        // Navigate to next page
        navigate("/next-page");
      } else {
        throw new Error(data.error || "Invalid CSV format");
      }
    } catch (err) {
      setError(`Error uploading file: ${err.message}`);
      setSelectedFile(null);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-50">
      <Card className="w-96">
        <CardContent className="pt-6">
          <h1 className="text-2xl font-bold mb-6 text-center">
            Upload Your CSV File
          </h1>

          {error && (
            <Alert variant="destructive" className="mb-4">
              <AlertDescription>{error}</AlertDescription>
            </Alert>
          )}

          <form onSubmit={handleUpload} className="space-y-6">
            <div className="space-y-4">
              <label className="block text-gray-700">Choose CSV File:</label>
              <div className="flex flex-col items-center space-y-4">
                <div className="w-full h-12 relative">
                  <input
                    type="file"
                    accept=".csv"
                    onChange={handleFileSelect}
                    className="absolute inset-0 w-full h-full opacity-0 cursor-pointer"
                    disabled={loading}
                  />
                  <Button
                    type="button"
                    variant="secondary"
                    className="w-full h-full"
                    disabled={loading}
                  >
                    Choose File
                  </Button>
                </div>

                {selectedFile && (
                  <p className="text-sm text-blue-600">
                    Selected file: {selectedFile.name}
                  </p>
                )}
              </div>
            </div>

            <Button
              type="submit"
              className="w-full bg-blue-600 hover:bg-blue-700 text-white"
              disabled={!selectedFile || loading}
            >
              {loading ? "Uploading..." : "Upload File"}
            </Button>
          </form>
        </CardContent>
      </Card>
    </div>
  );
};

export default FileUpload;
