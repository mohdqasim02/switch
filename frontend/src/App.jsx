import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import ToggleList from "./components/ToggleList";
import ToggleForm from "./components/ToggleForm";
import CreateToggle from "./pages/CreateToggle.jsx";

function App() {
  return (
      <Router>
        <Routes>
          <Route path="/" element={<ToggleList />} />
          <Route path="/new" element={<CreateToggle isEdit={false} />} />
          <Route path="/edit/:key" element={<ToggleForm isEdit={true} />} />
        </Routes>
      </Router>
  );
}

export default App;