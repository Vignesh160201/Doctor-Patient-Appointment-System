import Header from './Header';
import Footer from './Footer';
import hos from "../assets/back2.png";

function PatientHome() {
    return (
            
        <div  >
            <Header/>
            <img className='hospital'  src={hos} alt='hos'/> 
         <Footer/>
        </div>
       
    )
}
export default PatientHome;