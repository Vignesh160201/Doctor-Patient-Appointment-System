import { useEffect, useState } from "react";
import Header from "./Header";
import axios from "axios";
import { Button, Form, Modal, Table } from "react-bootstrap";
import 'reactjs-popup/dist/index.css';
import dateFormat from 'dateformat';
import Footer from "./Footer";

function Home() {
    useEffect(() => {
        getList();
    }, [])

    const [data, setData] = useState([]);
    const [id, setId] = useState("");
    const [updatedStatus, setUpdatedStatus] = useState("Pending");
    const [remark, setRemark] = useState("");

    const [isShow, setIsShow] = useState(false)
    
    const initModal = () => {
        return setIsShow(!isShow)
    }


    function getList() {
        var doctorName = localStorage.getItem('doctor-name')
        axios.get(`http://localhost:8083/appointment/getallappointment/${doctorName}`)
            .then(result => {
                localStorage.setItem('appointmentlist-details', JSON.stringify(result.data))
                setData(result.data)
                
            })
    }

    function handleUpdate(id) {

        
            setIsShow(!isShow)
            setId(id);

    }
    function updateStatus() {
        const token = JSON.parse(localStorage.getItem('doctor-token'))
        axios.put(`http://localhost:8082/doctor/updatestatus/${id}/${updatedStatus}/${remark}`,{},
        { headers: { Authorization: `Bearer ${token}` } })
            .then(result => {
                localStorage.setItem('updated-details', JSON.stringify(result.data))
                //console.log(result.data)
                getList();
            }).catch(error=>{
                //alert("something is missing")
            })
            setRemark("");
        setUpdatedStatus("");
        setIsShow(!isShow)

    }

    return (
        <div >
            <Header />
            <div className="container mt-5" style={{height:'100vh'}}>
                {
                    data.length ? <>
                
                <Table striped bordered hover variant="dark">
                    <thead className="text-white">
                        <tr>
                            <th>AppointmentId</th>
                            <th>Name</th>
                            <th>Age</th>
                            <th>Gender</th>
                            <th>Contact No.</th>
                            <th>Date of Appointment</th>
                            <th>TimeSlot</th>
                            <th>Status</th>
                            <th>Remark</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            data.map((e) =>

                                <tr>
                                    <td className="text-white">{e.appointmentId}</td>
                                    <td className="text-white">{e.patientName}</td>
                                    <td className="text-white">{e.age}</td>
                                    <td className="text-white">{e.gender}</td>
                                    <td className="text-white">{e.contactNo}</td>
                                    <td className="text-white">{e.dateOfAppointment}</td>
                                    <td className="text-white">{e.timeSlot}</td>
                                    <td className="text-white">{e.status}</td>
                                    <td className="text-white">{e.remark}</td>
                                    <td>
                                        {
                                            (e.dateOfAppointment>=dateFormat(new Date(), "yyyy-mm-dd"))?
                                        <Button variant="primary" type="submit" onClick={() => handleUpdate(e.appointmentId)}>
                                        Update
                                    </Button>:<></>
                                        }
                                        
                                    </td>
                                </tr>
                            )
                        }


                    </tbody>
                </Table></>:<>
                        <h1>No Appointments</h1>
                </>}
                
                <Modal show={isShow}>
                    <Modal.Header closeButton onClick={initModal}>
                        <Modal.Title>Update Details</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <Form>
                            <Form.Group className="mb-3" controlId="formBasicEmail">
                                <Form.Label>Status</Form.Label>
                                <Form.Select type="text" value={updatedStatus} onChange={(e) => setUpdatedStatus(e.target.value)}>
                                    <option value="Pending" >Pending</option>
                                    <option value="Verified">Verified</option>
                                    <option value="Rejected">Rejected</option>
                                </Form.Select>
                            </Form.Group>
                            <Form.Group className="mb-3" controlId="formBasicEmail">
                                <Form.Label>Remark</Form.Label>
                                <Form.Control type="text" value={remark} onChange={(e) => setRemark(e.target.value)} />
                            </Form.Group>
                        </Form>
                    </Modal.Body>
                    <Modal.Footer>
                        <Button variant="danger" onClick={initModal}>
                            Close
                        </Button>
                        <Button variant="primary" onClick={updateStatus}>
                            Update
                        </Button>
                    </Modal.Footer>
                </Modal>
            </div>
           <Footer/>
        </div>
    )
}
export default Home;