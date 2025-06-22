import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import api from "../api";

export default function ToggleList() {
  const [flags, setFlags] = useState([]);

  useEffect(() => {
    api.get("/").then(res => setFlags(res.data));
  }, []);

  return (
      <div className="p-6">
        <div className="flex justify-between mb-4">
          <h1 className="text-2xl font-bold">Feature Flags</h1>
          <Link to="/new" className="bg-blue-600 text-white px-4 py-2 rounded">+ Create</Link>
        </div>
        <ul className="space-y-3">
          {flags.map(flag => (
              <li key={flag.key} className="p-4 border rounded flex justify-between items-center">
                <div>
                  <p><strong>{flag.name}</strong> ({flag.key})</p>
                  <p className="text-sm text-gray-500">{flag.enabled ? "Enabled" : "Disabled"} - {flag.type}</p>
                </div>
                <Link to={`/edit/${flag.key}`} className="text-blue-600 underline">Edit</Link>
              </li>
          ))}
        </ul>
      </div>
  );
}
