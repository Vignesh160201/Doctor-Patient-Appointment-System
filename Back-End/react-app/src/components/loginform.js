import React, { useState } from "react";
import "../css/login.css";
import Header from './Header';
import Footer from "./Footer";
import axios from 'axios';
import { Alert, Modal } from "react-bootstrap";
import { Button } from 'react-bootstrap'
import { useNavigate } from 'react-router-dom';


function Doctor() {

    let usenavigate = useNavigate();
    const [user, setUser] = useState('');
    const [password, setPassword] = useState('');
    const [show, setShow] = useState(false);
    const [isShow, setIsShow] = useState(false)

    const handleSubmit = (e) => {
        e.preventDefault();
        axios.post('http://localhost:8400/auth/authenticate', {
            password: password,
            userName: user
        }).then(result => {
            localStorage.setItem('doctor-token', JSON.stringify(result.data.token));
            localStorage.setItem('doctor-name', user);

            //alert("Login Success");
            setIsShow(!isShow)
            
        })
            .catch(error => {
                setShow(true);
                //alert(error.response.data.message)
            })
    }
    function initModal(){
        setIsShow(!isShow)
        usenavigate('/home');
        
    }
    return (
        <div>
            <Header/>
            <Alert variant="danger" show={show} onClose={() => setShow(false)} dismissible>
              Enter correct Credentials
              </Alert>
        <div className="page">
        <div className="cover">
            <h1>Login</h1>
            <form onSubmit={handleSubmit}>
            <input style={{height: '8px'}} type="text"  onChange={(e)=>setUser(e.target.value)} value={user} placeholder="UserName" />
            <input style={{margin:'20px', height: '8px'}} type="password" onChange={(e)=>setPassword(e.target.value)} value={password} placeholder="Password" />
            <Button className="login-btn btn-success" style={{marginTop:'20px'}} type="submit">Login</Button>
            </form>
        </div>
        </div>
        
        <Modal show={isShow} style={{width: '300px',
   height: '300px',
   position: 'absolute',
   left: '50%',
   top: '50%', 
   marginLeft: '-150px',
   marginTop: '-150px'}}>
                    <Modal.Body>
                        <Modal.Title className="text-center">Login Successfully</Modal.Title>
                        <br/><br/>
                        <div className="text-center" >
                        <Button className="fs-5" style={{padding:'4px 20px'}} variant="success" onClick={initModal}>
                            OK
                        </Button>
                        </div>
                    </Modal.Body>
                    
                </Modal>
              
         <div><Footer/></div> 
        </div >
        
    )

}
export default Doctor;