import Footer from "./Footer";
import Header from "./Header";
import contact from "../assets/contact.jpg";
function Contactus(){

    return(
        <div>
            <div><Header/></div>
            <img className='hospital' src={contact}></img>
            <div><Footer/></div>
        </div>
    );

    
}
export default Contactus;