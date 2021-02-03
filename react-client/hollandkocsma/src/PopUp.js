import React from 'react';
import Modal from "react-bootstrap/Modal";
import "bootstrap/dist/css/bootstrap.min.css";

function PopUp({ title, body }) {

  return (
    <Modal show={true}>
      <Modal.Header>{title}</Modal.Header>
      <Modal.Body>{body}</Modal.Body>
      <Modal.Footer>
        <button>OK</button>
      </Modal.Footer>
    </Modal>
  );
}

export default PopUp;
