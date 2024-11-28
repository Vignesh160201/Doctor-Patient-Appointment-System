import { useEffect, useState } from "react";
import { Alert, Button, Form, Table } from "react-bootstrap";
import Header from "./Header";
import Footer from './Footer';
import axios from "axios";

function Appointment() {

  useEffect(()=>{
    localStorage.clear('appointment-details')
  })
    const [appointmentId,setAppointmentId]=useState('')
    const [data,setData]=useState([]);
    const [show, setShow] = useState(false);

    const handleSearch=(e)=>{
        e.preventDefault();
        axios.get(`http://localhost:8081/patient/viewappointment/${appointmentId}`)
        .then(result=>{
            localStorage.setItem('appointment-details',JSON.stringify(result.data))
          //console.log(result.data)
            setData(result.data)
        }).catch(error=>{
          setShow(true)
            //alert(error.response.data.message)
        })

    }

    return (
      
        <div>
            <Header/>
            <Alert variant="danger" show={show} onClose={() => setShow(false)} dismissible>
              Appointment not found for Id : {appointmentId}
              </Alert>
            <div className="container clr" style={{height:'100vh'}}>
            <Form className="form">
              <h1 className="text-dark">Search  for Appointments </h1>
            <Form.Group className="mb-3 mt-5" controlId="formBasicEmail">
                    <Form.Control type="number" value={appointmentId} onChange={(e)=>setAppointmentId(e.target.value)} placeholder="Enter appointment Id" />
                </Form.Group>
                <Button className="mb-3 btn btn-dark" variant="primary" type="submit" onClick={handleSearch}>
                    Search
                </Button>
            </Form>

            {
                localStorage.getItem('appointment-details')?
                <><Table striped bordered hover variant="dark">
                <thead className="text-white">
                  <tr>
                    <th>AppointmentId</th>
                    <th>Name</th>
                    <th>Age</th>
                    <th>Gender</th>
                    <th>Contact No.</th>
                    <th>Dr. Name</th>
                    <th>Date of Appointment</th>
                    <th>TimeSlot</th>
                    <th>Status</th>
                    <th>Remark</th>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <td className="text-white">{data.appointmentId}</td>
                    <td className="text-white">{data.patientName}</td>
                    <td className="text-white">{data.age}</td>
                    <td className="text-white">{data.gender}</td>
                    <td className="text-white">{data.contactNo}</td>
                    <td className="text-white">Dr. {data.doctorName}</td>
                    <td className="text-white">{data.dateOfAppointment}</td>
                    <td className="text-white">{data.timeSlot}</td>
                    <td className="text-white">{data.status}</td>
                    <td className="text-white">{data.remark}</td>
                  </tr>
                  
                </tbody>
              </Table></>:<></>
            }
        </div>
        <div>

        <Footer/>
        </div>
        </div>
        
    )
}
export default Appointment;