import './App.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom'
import Home from './components/Home'
import AboutUs from'./components/AboutUs'
import ContactUs from'./components/ContactUs'
import Appointment from './components/Appointment'
import Booking from './components/Booking'
import Profile from './components/Profile';
import Home1 from './components/PatientHome';
import LoginForm from './components/loginform';


function App() {
  return (
    <div className="App" >
       <BrowserRouter>
      
      <Routes>
      <Route path="/" element={<Home1/>}></Route>
    
      <Route path="/home" element={<Home/>}></Route>
      <Route path="/doctor" element={<LoginForm />}></Route>
      <Route path="/viewappointment" element={<Appointment/>}></Route>
      <Route path="/bookappointment" element={<Booking/>}></Route>
      <Route path="/editprofile" element={<Profile/>}></Route>
      <Route path="/about" element={<AboutUs/>}></Route>
      <Route path="/contact" element={<ContactUs/>}></Route>
      </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;


