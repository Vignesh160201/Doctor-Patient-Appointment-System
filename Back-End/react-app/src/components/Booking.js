import Header from './Header';
import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios'
import dateFormat from 'dateformat';
import Footer from './Footer';
import { Button, Modal } from 'react-bootstrap';


function Booking() {
    let usenavigate = useNavigate();

    const [patientName, setPatientName] = useState()
    const [doctorId, setDoctorId] = useState('docname')
    const [dateOfAppointment, setDateOfAppointment] = useState()
    const [timeSlot, setTimeSlot] = useState('tslot')
    const [specialization, setSpecialization] = useState('spec')
    const [age, setAge]=useState()
    const [gender, setGender]=useState('gender')
    const [contactNo, setContactNo]=useState()
    const [doctorDetails, setDoctoDetails] = useState([]);
    const [spec, setSpec]=useState([])
    const [errors, setErrors] = useState({})
    const validName = /^[a-zA-Z ]{2,40}$/;
    const validContact = /^\d{10}$/;
    const [dateMsg, setDateMsg] = useState('')
    const [isShow, setIsShow] = useState(false)
    const [appId, setAppId] = useState()

    useEffect(() => {
        axios.get('http://localhost:8082/doctor/distinctspecialization')
            .then(result => {
                setDoctoDetails(result.data)
                //console.log(result.data)
            })
    }, [])

    useEffect(()=>{
        axios.get(`http://localhost:8082/doctor/getdoctorbyspecialist/${specialization}`)
            .then(result => {
                setSpec(result.data)
            })
    },[specialization])
    function validate() {
        const errors = {};

        if(!validName.test(patientName)) {
            errors.patientName = "Enter atleast two characters"
        }
        if(!patientName) {
            errors.patientName = "Name is Required"
        }
        if(!validContact.test(contactNo)) {
            errors.contactNo = "Enter correct mobile number"
        }
        if(!validContact) {
            errors.contactNo = "Phone no is Required"
        }
       
        if(age<=0){
            errors.age = "Enter correct Age"
        }
        if(!age){
            errors.age = "Age is Required"
        }
        if(gender==='gender') {
            errors.gender = "Select Gender"
        }
        if(specialization==='spec') {
            errors.specialization = "Select Specialization"
        }
        if(doctorId==='docname') {
            errors.doctorId = "Select Doctor Name"
        }
        if(timeSlot==="tslot") {
            errors.timeSlot = "Select Time slot"
        }
        if(!dateOfAppointment) {
            errors.dateOfAppointment = "Date of Appointment is Required"
        }
        setErrors(errors)
    }
    function initModal(){
        setIsShow(!isShow)
        usenavigate('/viewappointment')

    }
    const handleSubmit = (e) => {
        e.preventDefault();
        validate();
        if(!Object.keys(errors).length) {
        axios.post('http://localhost:8081/patient/makeappointment', {
            patientName,
            doctorId,
            dateOfAppointment,
            timeSlot,
            specialization,
            age,
            gender,
            contactNo
        }).then(result => {
            //window.alert("Appointment booked succesfully.\nYour Appointment Id is " + result.data.appointmentId)
            setAppId(result.data.appointmentId)
            //console.log(result.data)
            setIsShow(!isShow)
            
        }).catch(error=>{
            
        })
        }
    }
    

    return (
        <div className=''>
        <div>
        <Header />
        </div>
        <div className=' pb-5'>
            <div className="offset-lg-3 col-lg-6 pt-5 pb-5">
                <form className="container">
                    <div className="card">
                        <div className="card-header">
                            <h1>Book an Appointment</h1>
                        </div>
                        <div className="card-body">

                            <div className="row">
                                <div className="col-lg-6">
                                    <div className="form-group m-2">
                                        <input className="form-control" type="text" value={patientName} onChange={(e) => setPatientName(e.target.value)} placeholder="Enter name"></input>
                                        <p style={{color:'red'}}>{errors.patientName}</p>
                                    </div>
                                </div>
                                <div className="col-lg-6">
                                    <div className="form-group m-2">
                                        <input type="number" className="form-control" value={contactNo} onChange={(e) => setContactNo(e.target.value)} placeholder="Phone No"></input>
                                        <p style={{color:'red'}}>{errors.contactNo}</p>
                                    </div>
                                </div>
                                
                                <div className="col-lg-6">
                                    <div className="form-group m-2">
                                        <input className="form-control" type="number" value={age} onChange={(e) => setAge(e.target.value)} placeholder="Enter Age"></input>
                                        <p style={{color:'red'}}>{errors.age}</p>
                                    </div>
                                </div>
                                <div className="col-lg-6">
                                    <div className="form-group m-2">
                                        <select className="form-control text-center" value={gender} onChange={(e) => setGender(e.target.value)}>
                                            <option value="gender">---Select Gender---</option>
                                            <option value="Male">Male</option>
                                            <option value="Female">Female</option>
                                            <option value="TransGender">Other</option>
                                        </select>
                                        <p style={{color:'red'}}>{errors.gender}</p>
                                    </div>
                                </div>
                                
                                <div className="col-lg-6">
                                    <div className="form-group m-2">
                                        <select className="form-control text-center" onChange={(e) => setSpecialization(e.target.value)}>
                                            <option value="spec">---Select Specialization---</option>
                                            {
                                                doctorDetails.map((item) =>

                                                    <option value={item}>{item}</option>
                                                )
                                            }
                                        </select>
                                        <p style={{color:'red'}}>{errors.specialization}</p>
                                    </div>
                                </div>

                                <div className="col-lg-6 ">
                                    <div className="form-group m-2">
                                        <select className="form-control text-center" value={doctorId} onChange={(e) => setDoctorId(e.target.value)}>
                                            <option value="docname">---Select Doctor Name---</option>

                                            {
                                                spec.map((item) =>

                                                    <option value={item.doctorName}>{item.doctorName}</option>
                                                )
                                            }
                                        </select>
                                        <p style={{color:'red'}}>{errors.doctorId}</p>
                                    </div>
                                </div>
                                <div className="col-lg-6">
                                    <div className="form-group m-2">
                                        
                                     <input className="form-control" type="date" value={dateOfAppointment} onChange={(e) => {
                                          const today = new Date()
                                          setDateMsg('')
                                            const choosendate = new Date(e.target.value)
                                            
                                            if(e.target.value<dateFormat(today, "yyyy-mm-dd"))
                                            {
                                                setDateMsg("you can't choose passed date")                                           }
                                            else if(choosendate.getDay()===0) {
                                                setDateMsg("you cannot select sunday as your date of appointment")
                                            }
                                            else {
                                            setDateOfAppointment(e.target.value)
                                            }}}></input> 
                                            {
                                                dateMsg ? 
                                                <p style={{color:'red'}}>{dateMsg}</p> :
                                                <p style={{color:'red'}}>{errors.dateOfAppointment}</p>
                                            }
                                            
                                    </div>
                                </div>
                                <div className="col-lg-6">
                                    <div className="form-group m-2">
                                        <select className="form-control text-center" value={timeSlot} onChange={(e) => setTimeSlot(e.target.value)}>
                                        <option value="tslot">---Select Prefered Time---</option>
                                            <option value="09am to 10am">9am to 10am</option>
                                            <option value="10am to 11am">10am to 11am</option>
                                            <option value="04pm to 05pm">4pm to 5pm</option>
                                            <option value="05pm to 06pm">5pm to 6pm</option>
                                        </select>
                                        <p style={{color:'red'}}>{errors.timeSlot}</p>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <div className="card-footer">
                            <button type="submit" className="btn btn-dark" onClick={handleSubmit}>Submit</button> 
                        </div>
                    </div>
                </form>
            </div>
            </div>
            <Modal show={isShow} style={{width: '350px',
   height: '300px',
   position: 'absolute',
   left: '50%',
   top: '50%', 
   marginLeft: '-150px',
   marginTop: '-150px'}}>
                    <Modal.Body>
                        <Modal.Title className="text-center">Appointment Booked Succesfully!!!<br/> Your Appointment Id is : {appId}</Modal.Title>
                        <br/><br/>
                        <div className="text-center" >
                        <Button className="fs-5" style={{padding:'4px 20px'}} variant="success" onClick={initModal}>
                            OK
                        </Button>
                        </div>
                    </Modal.Body>
                    
                </Modal>
        <Footer/>
    </div>
    )
}
export default Booking;