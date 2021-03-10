import React from "react";

function Accordion({ title, children }) {
  return (
    <div class="accordion accordion-flush" id="accordionFlushExample">
      <div class="accordion-item">
        <h2 class="accordion-header" id="flush-headingOne">
          <div
            class="accordion-button collapsed text-white bg-transparent arrowButton"
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
          class="accordion-collapse collapse text-white"
          aria-labelledby="flush-headingOne"
          data-bs-parent="#accordionFlushExample"
        >
          <div class="accordion-body">{children}</div>
        </div>
      </div>
    </div>
  );
}

export default Accordion;
