import axios from "axios";
import React, { useEffect,useState } from "react";
import { Link, useParams } from "react-router-dom";

export default function ViewPatient() {

    const { id } = useParams();

    const [patient, setPatient] = useState({
        firstName: "",
        lastName: "",
        age: 0,
        symptomsDescription: "",
        diagnosis: ""
      });

  useEffect(() => {
    loadPatient();
  }, []);

  const loadPatient = async () => {
    const result = await axios.get(`../../api/patients/${id}`);
    setPatient(result.data);
  };

  return (
    <div className="container">
      <div className="row">
        <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
          <h2 className="text-center m-4">Patient with id {patient.id}</h2>
          <div className="card">
            <div className="card-header">
              <ul className="list-group list-group-flush">
                <li className="list-group-item">
                  <b>First Name: </b>
                  {patient.firstName}
                </li>
                <li className="list-group-item">
                  <b>Last Name: </b>
                  {patient.lastName}
                </li>
                <li className="list-group-item">
                  <b>Age: </b>
                  {patient.age}
                </li>
                <li className="list-group-item">
                  <b>Symptoms Description: </b>
                  {patient.symptomsDescription}
                </li>
                <li className="list-group-item">
                  <b>Diagnosis: </b>
                  {patient.diagnosis}
                </li>
              </ul>
            </div>
          </div>
          <Link className="btn btn-primary my-2" to={"/"}>
            Back to Home
          </Link>
        </div>
      </div>
    </div>
  );
}