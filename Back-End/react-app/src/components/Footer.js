import React from 'react';
import { Link, useNavigate } from 'react-router-dom';

export default function Footer() {
  let usenavigate = useNavigate();

  return (
    <>
<footer className="text-center text-lg-start bg-light text-muted">
  <section className="d-flex justify-content-center justify-content-lg-between p-4 border-bottom">
    <div className="me-5 d-none d-lg-block">
      <span>Get connected with us on social networks:</span>
    </div>

    <div>
      <a href="" className="me-4 text-reset">
        <i className="fab fa-facebook-f"></i>
      </a>
      <a href="" className="me-4 text-reset">
        <i className="fab fa-twitter"></i>
      </a>
      <a href="" className="me-4 text-reset">
        <i className="fab fa-google"></i>
      </a>
      <a href="" className="me-4 text-reset">
        <i className="fab fa-instagram"></i>
      </a>
      <a href="" className="me-4 text-reset">
        <i className="fab fa-linkedin"></i>
      </a>
      <a href="" className="me-4 text-reset">
        <i className="fab fa-github"></i>
      </a>
    </div>
  </section>

  <section className="">
    <div className="container text-center text-md-start mt-5">
      <div className="row mt-3">
        <div className="col-md-3 col-lg-4 col-xl-3 mx-auto mb-4">
          <h6 className="text-uppercase fw-bold mb-4">
            <i className="fas fa-gem me-3"></i>POD 1 Hospital, India
          </h6>
          <p>
          Healthcare facility that provides specialized medical and nursing care to patients. 
          we offer treatment to patients with specialized staff and equipment.
          </p>
        </div>
        <div className="col-md-2 col-lg-2 col-xl-2 mx-auto mb-4">
          <h6 className="text-uppercase fw-bold mb-4">
            Opening hours
          </h6>
          <p>
            Monday to Saturday 9:00 AM to 5:00 PM
          </p>
          <p>
            Sunday: Closed
          </p>
        </div>

        <div className="col-md-3 col-lg-2 col-xl-2 mx-auto mb-4">
          <h6 className="text-uppercase fw-bold mb-4">
            Useful links
          </h6>
          <p onClick={(e)=>{usenavigate('/contact')}}>Contact Us</p>
          <p onClick={(e)=>{usenavigate('/about')}}>About Us</p>
          
        </div>

        <div className="col-md-4 col-lg-3 col-xl-3 mx-auto mb-md-0 mb-4">
          
          <h6 className="text-uppercase fw-bold mb-4">Contact</h6>
          <p><i className="fas fa-home me-3"></i> Shahapur,Mumbai</p>
          <p>
            <i className="fas fa-envelope me-3"></i>
            pod1@cognizant.com
          </p>
          <p><i className="fas fa-phone me-3"></i> +91 8169552378</p>
         
        </div>
      </div>
    </div>
  </section>

</footer>
    </>
  );
}