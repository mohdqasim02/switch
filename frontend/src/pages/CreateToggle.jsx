import { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "../api";

export default function CreateToggle() {
  const [flag, setFlag] = useState({
    flagKey: "",
    name: "",
    description: "",
    type: "BOOLEAN",
    isEnabled: false,
    createdBy: "",
    createdAt: "",
  });

  const navigate = useNavigate();

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    setFlag((prev) => ({
      ...prev,
      [name]: type === "checkbox" ? checked : value,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    // Auto fill createdAt if left empty
    const payload = {
      ...flag,
      createdAt: flag.createdAt || new Date().toISOString(),
    };

    try {
      await axios.post("/create", payload);
      alert("Feature flag created!");
      navigate("/");
    } catch (err) {
      console.error(err);
      alert("Failed to create flag");
    }
  };

  return (
      <div className="max-w-xl mx-auto p-6">
        <h2 className="text-2xl font-bold mb-4">Create Feature Toggle</h2>
        <form onSubmit={handleSubmit} className="space-y-4">
          <input
              name="flagKey"
              placeholder="Flag Key"
              value={flag.flagKey}
              onChange={handleChange}
              required
              className="w-full p-2 border rounded"
          />
          <input
              name="name"
              placeholder="Name"
              value={flag.name}
              onChange={handleChange}
              required
              className="w-full p-2 border rounded"
          />
          <textarea
              name="description"
              placeholder="Description"
              value={flag.description}
              onChange={handleChange}
              rows={3}
              className="w-full p-2 border rounded"
          />
          <select
              name="type"
              value={flag.type}
              onChange={handleChange}
              className="w-full p-2 border rounded"
          >
            <option value="BOOLEAN">BOOLEAN</option>
            <option value="STRING">STRING</option>
            <option value="NUMBER">NUMBER</option>
          </select>
          <label className="flex items-center space-x-2">
            <input
                type="checkbox"
                name="isEnabled"
                checked={flag.isEnabled}
                onChange={handleChange}
            />
            <span>Enabled</span>
          </label>
          <input
              name="createdBy"
              placeholder="Created By"
              value={flag.createdBy}
              onChange={handleChange}
              required
              className="w-full p-2 border rounded"
          />
          <input
              type="datetime-local"
              name="createdAt"
              value={flag.createdAt}
              onChange={handleChange}
              className="w-full p-2 border rounded"
          />
          <button
              type="submit"
              className="bg-blue-600 text-white px-4 py-2 rounded"
          >
            Create Toggle
          </button>
        </form>
      </div>
  );
}
