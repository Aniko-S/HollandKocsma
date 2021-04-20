import React from "react";

function Accordion({ title, children }) {
  return (
    <div className="accordion accordion-flush" id="accordionFlushExample">
      <div className="accordion-item">
        <h2 className="accordion-header" id="flush-headingOne">
          <div
            className="accordion-button collapsed text-white bg-transparent arrowButton"
            role="button"
            data-bs-toggle="collapse"
            data-bs-target="#flush-collapseOne"
            aria-expanded="false"
            aria-controls="flush-collapseOne"
          >
            {title}
          </div>
        </h2>
        <div
          id="flush-collapseOne"
          className="accordion-collapse collapse text-white"
          aria-labelledby="flush-headingOne"
          data-bs-parent="#accordionFlushExample"
        >
          <div className="accordion-body">{children}</div>
        </div>
      </div>
    </div>
  );
}

export default Accordion;
