import axios from "axios";
import { useEffect, useState } from "react";
import { Button, Form, Modal } from "react-bootstrap";
import { useNavigate } from 'react-router-dom';

function Profile() {
    const [isShow, setIsShow] = useState(true)
    const [isShow1, setIsShow1] = useState(false)
    var usenavigate = useNavigate();

    const [doctorQualification, setDoctorQualification] = useState('')
    const [doctorSpecialist, setDoctorSpecialist]=useState('')
    const [doctorEmail, setDoctorEmail]=useState('')
    const [doctorContactNo, setDoctorContactNo]=useState('')
   

    const token = JSON.parse(localStorage.getItem('doctor-token'))
    const name=localStorage.getItem('doctor-name')
    useEffect(()=>{
         axios.get(`http://localhost:8082/doctor/getdoctor/${name}`,
        { headers: { Authorization: `Bearer ${token}` } }
        )
        .then(result=>{
            localStorage.setItem('doctor-dr-details',JSON.stringify(result.data))
            setDoctorContactNo(result.data.doctorContactNo)
            setDoctorEmail(result.data.doctorEmail)
            setDoctorQualification(result.data.doctorQualification)
            setDoctorSpecialist(result.data.doctorSpecialist)
        }).catch(error=>{
            alert(error)
        })
    },[])

    function updatedetails() {
        axios.put(`http://localhost:8082/doctor/updatedoctor/${name}`, {
            doctorName: name,
            doctorQualification,
            doctorSpecialist,
            doctorContactNo,
            doctorEmail
        },
        { headers: { Authorization: `Bearer ${token}` } }
        ).then(result=>{
            console.log(result.data)
            //alert("details updated")
            setIsShow1(!isShow1)
           // usenavigate('/home');
       setIsShow(!isShow)
        }).catch(error=>{
            alert("error")
        })
    }
    const initModal = () => {
        usenavigate('/home');
      return setIsShow(!isShow)
    }

    function initModal1(){
        setIsShow1(!isShow1)
        usenavigate('/home');
        
    }
    return(
        <div>
            <h1>Profile</h1>

            <Modal show={isShow}>
        <Modal.Header closeButton onClick={initModal}>
          <Modal.Title>Update your Profile here</Modal.Title>
        </Modal.Header>
        <Modal.Body>
        <Form>
            <Form.Group className="mb-3" controlId="formBasicEmail">
                    <Form.Label>Qualification</Form.Label>
                    <Form.Control type="text" value={doctorQualification} onChange={(e)=>setDoctorQualification(e.target.value)}/>
                </Form.Group>
                <Form.Group className="mb-3" controlId="formBasicEmail">
                    <Form.Label>Specialization</Form.Label>
                    <Form.Control type="text" value={doctorSpecialist} onChange={(e)=>setDoctorSpecialist(e.target.value)}/>
                </Form.Group>
                <Form.Group className="mb-3" controlId="formBasicEmail">
                    <Form.Label>Email</Form.Label>
                    <Form.Control type="email"  value={doctorEmail} onChange={(e)=>setDoctorEmail(e.target.value)}/>
                </Form.Group>
                <Form.Group className="mb-3" controlId="formBasicEmail">
                    <Form.Label>Contact No.</Form.Label>
                    <Form.Control type="number" value={doctorContactNo} onChange={(e)=>setDoctorContactNo(e.target.value)}/>
                </Form.Group>
        </Form>        
        </Modal.Body>
        <Modal.Footer>
          <Button variant="danger" onClick={initModal}>
            Close
          </Button>
          <Button variant="success" onClick={updatedetails}>
            Update
          </Button>
        </Modal.Footer>
      </Modal>
      
      <Modal show={isShow1} style={{width: '300px',
   height: '300px',
   position: 'absolute',
   left: '50%',
   top: '50%', 
   marginLeft: '-150px',
   marginTop: '-150px'}}>
                    <Modal.Body>
                        <Modal.Title className="text-center">Update Successfully</Modal.Title>
                        <br/><br/>
                        <div className="text-center" >
                        <Button className="fs-5" style={{padding:'4px 20px'}} variant="success" onClick={initModal1}>
                            OK
                        </Button>
                        </div>
                    </Modal.Body>
                    
                </Modal>
        </div>
    )
}
export default Profile;