import { Navbar, Nav, Container, NavDropdown } from 'react-bootstrap'
import { Link } from 'react-router-dom'
import { useNavigate } from 'react-router-dom';
import logo from '../assets/logo.svg'
import doci from '../assets/person.svg'
import logi from '../assets/power.svg'
import editi from '../assets/pen.svg'



function Header() {
  var usenavigate = useNavigate();
  var user = localStorage.getItem('doctor-name');

  function logoutme() {
    localStorage.clear();
    usenavigate('/doctor');

  }

  function editprofile() {
    usenavigate('/editprofile')
  }

  return (
    <div>
      <Navbar className="navbar navbar-dark bt"  >
        <Container>
          <Nav className="me-auto navbar_wrapper">
            <img className='logo m-3'  src={logo} alt='logo'/>
            {
              localStorage.getItem('doctor-token') ? <>
              <Link  className='text-white fs-5 home' to="/home">Home</Link>
              </> : <>
              <Link className='text-white fs-5 home' to="/">
              Home</Link>
                <Link className='text-white fs-5 home ' to="/doctor">Doctor</Link>
                <Link className='text-white fs-5 home' to="/viewappointment">View Appointment</Link>
                <Link className='text-white fs-5 home' to="/bookappointment">Book Appointment</Link>
                <Link className='text-white fs-5 home' to="/about">About Us</Link>
                <Link className='text-white fs-5 home' to="/contact">Contact Us</Link>
              </>
            }
          </Nav>
          {localStorage.getItem('doctor-token')?<>
          <Nav className='ms-auto navbar-deco'><img className='doci' src={doci} alt='doci'/>
                  <NavDropdown style={{color:'white'}} title={user}>
                    <NavDropdown.Item onClick={logoutme}><img className='logo' src={logi} alt='logi' />LogOut</NavDropdown.Item>
                    
                      <Link to="/editprofile"><NavDropdown.Item onClick={editprofile}><img className='logo' src={editi} alt='editi'/>Profile</NavDropdown.Item></Link>    
                  </NavDropdown>
          </Nav></>:<></>
          }
        </Container>
      </Navbar> 
    </div>
  )
}
export default Header;