import Footer from "./Footer";
import Header from "./Header";
import about from "../assets/about.jpg";

function AboutUs(){

    return(
        <div>
            <div><Header/></div>
            <img className="hospital" src={about}/>
            <div><Footer/></div>
        </div>
    );

    
}
export default AboutUs;