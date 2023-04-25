import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import Navbar from "./layout/Navbar";
import Home from "./pages/Home";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import AddPatient from "./patients/AddPatient";
import EditPatient from "./patients/EditPatient";
import ViewPatient from "./patients/ViewPatient";

function App() {
  return (
    <div className="App">
      <Router>
        <Navbar/>
        <Routes>
          <Route exact path="/" element={<Home />} />
          <Route exact path="/addpatient" element={<AddPatient />} />
          <Route exact path="/editpatient/:id" element={<EditPatient />} />
          <Route exact path="/viewpatient/:id" element={<ViewPatient />} />
        </Routes>
      </Router>
    </div>
  );
}

export default App;