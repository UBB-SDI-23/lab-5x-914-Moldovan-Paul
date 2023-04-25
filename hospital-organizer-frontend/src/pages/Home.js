import React, { useEffect, useState } from "react";
import axios from "axios";
import { Link } from "react-router-dom";

export default function Home() {
  const [patients, setPatients] = useState([]);
  const [hospitals, setHospitals] = useState([]);
  const [sortRow, setSortRow] = useState("");

  useEffect(() => {
    loadPatients();
    loadHospitals();
  }, []);

  const loadPatients = async () => {
    const result = await axios.get("../api/patients");
    const sortedPatients = sortPatients(result.data);
    setPatients(sortedPatients);
  };

  const loadHospitals = async () => {
    const result = await axios.get("../../api/hospitals");
    setHospitals(result.data);
  };

  const deletePatient = async (id) => {
    await axios.delete(`../../api/patients/${id}`);
    loadPatients();
  };

  const sortPatients = (patients) => {
    if (!sortRow) {
      return patients;
    }

    return patients.sort((a, b) => {
      if (a[sortRow] < b[sortRow]) {
        return -1;
      }
      if (a[sortRow] > b[sortRow]) {
        return 1;
      }
      return 0;
    });
  };

  const handleSortChange = (event) => {
    setSortRow(event.target.value);
    const sortedPatients = sortPatients(patients);
    setPatients(sortedPatients);
  };

  return (
  <>
    <div className="container">
      <div className="py-4">
        <table className="table border shadow">
          <thead>
            <tr>
              <th scope="col">ID</th>
              <th scope="col">First Name</th>
              <th scope="col">Last Name</th>
              <th scope="col">Age</th>
              <th scope="col">Symptoms Description</th>
              <th scope="col">Diagnosis</th>
              <th scope="col">Hospital ID</th>
              <th scope="col">
              Sort by:
              <select value={sortRow} onChange={handleSortChange}>
                <option value="">None</option>
                <option value="id">ID</option>
                <option value="firstName">First Name</option>
                <option value="lastName">Last Name</option>
                <option value="age">Age</option>
                <option value="symptomsDescription">Symptoms Description</option>
                <option value="diagnosis">Diagnosis</option>
                <option value="hospitalId">Hospital ID</option>
              </select>
            </th>
          </tr>
        </thead>
      <tbody>
        {patients.map((patient, index) => (
          <tr key={index}>
            <th scope="row">{patient.id}</th>
            <td>{patient.firstName}</td>
            <td>{patient.lastName}</td>
            <td>{patient.age}</td>
            <td>{patient.symptomsDescription}</td>
            <td>{patient.diagnosis}</td>
            <td>{patient.hospitalId}</td>
            <td>
              <Link
                className="btn btn-primary mx-2"
                to={`/viewpatient/${patient.id}`}
              >
                View
              </Link>
              <Link
                className="btn btn-outline-primary mx-2"
                to={`/editpatient/${patient.id}`}
              >
                Edit
              </Link>
              <button
                className="btn btn-danger mx-2"
                onClick={() => deletePatient(patient.id)}
              >
                Delete
              </button>
            </td>
          </tr>
        ))}
        </tbody>
      </table>
    </div>
  </div>

  <div className="container">
      <div className="py-4">
        <table className="table border shadow">
          <thead>
            <tr>
              <th scope="col">ID</th>
              <th scope="col">Name</th>
              <th scope="col">Address</th>
              <th scope="col">Specialties</th>
              <th scope="col">Private</th>
              <th scope="col">Takes Emergencies</th>
              <th scope="col">Maximum Capacity</th>
          </tr>
        </thead>
      <tbody>
        {hospitals.map((hospital, index) => (
          <tr key={index}>
            <th scope="row">{hospital.id}</th>
            <td>{hospital.name}</td>
            <td>{hospital.address}</td>
            <td>{hospital.specialties}</td>
            <td>{hospital.privateHospital}</td>
            <td>{hospital.takesEmergencies}</td>
            <td>{hospital.maximumCapacity}</td>
          </tr>
        ))}
      </tbody>
      </table>
    </div>
  </div>
</>
);
}