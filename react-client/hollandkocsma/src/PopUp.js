import React from 'react';
import Modal from "react-bootstrap/Modal";
import "bootstrap/dist/css/bootstrap.min.css";
import { Link } from 'react-router-dom';

function PopUp({ title, body }) {

  return (
    <Modal show={true}>
      <Modal.Header><h3>{title}</h3></Modal.Header>
      <Modal.Body>{body}</Modal.Body>
      <Modal.Footer>
        <Link to=''>
          <button className='btn okbutton'>OK</button>
        </Link>
      </Modal.Footer>
    </Modal>
  );
}

export default PopUp;
