import { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import api from "../api";

export default function ToggleForm({ isEdit }) {
  const [flag, setFlag] = useState({
    key: "",
    name: "",
    enabled: false,
    type: "BOOLEAN",
  });

  const navigate = useNavigate();
  const { key } = useParams();

  useEffect(() => {
    if (isEdit && key) {
      api.get("/").then(res => {
        const existing = res.data.find(f => f.key === key);
        if (existing) setFlag(existing);
      });
    }
  }, [isEdit, key]);

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    setFlag((prev) => ({
      ...prev,
      [name]: type === "checkbox" ? checked : value,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (isEdit) {
      await api.post("/", flag); // simplified: upserts the flag
    } else {
      await api.post("/", flag);
    }
    navigate("/");
  };

  return (
      <div className="p-6 max-w-xl mx-auto">
        <h1 className="text-xl font-bold mb-4">{isEdit ? "Edit" : "Create"} Feature Flag</h1>
        <form onSubmit={handleSubmit} className="space-y-4">
          {!isEdit && (
              <input
                  name="key"
                  placeholder="Key"
                  required
                  value={flag.key}
                  onChange={handleChange}
                  className="w-full border p-2 rounded"
              />
          )}
          <input
              name="name"
              placeholder="Name"
              required
              value={flag.name}
              onChange={handleChange}
              className="w-full border p-2 rounded"
          />
          <select
              name="type"
              value={flag.type}
              onChange={handleChange}
              className="w-full border p-2 rounded"
          >
            <option value="BOOLEAN">BOOLEAN</option>
            <option value="STRING">STRING</option>
            <option value="NUMBER">NUMBER</option>
          </select>
          <label className="flex items-center space-x-2">
            <input
                type="checkbox"
                name="enabled"
                checked={flag.enabled}
                onChange={handleChange}
            />
            <span>Enabled</span>
          </label>
          <button type="submit" className="bg-blue-600 text-white px-4 py-2 rounded">
            {isEdit ? "Update" : "Create"}
          </button>
        </form>
      </div>
  );
}

