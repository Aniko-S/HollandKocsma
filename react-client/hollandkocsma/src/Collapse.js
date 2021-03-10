import React from "react";

function Collapse({ title, id, children }) {
  return (
    <>
      <div
        class="text-white my-2"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target={`#${id}`}
        aria-expanded="false"
        aria-controls="collapseExample"
      >
        <b>
          <u>{title}</u>
        </b>
      </div>

      <div class="collapse" id={id}>
        <div class="card card-body bg-transparent">{children}</div>
      </div>
    </>
  );
}

export default Collapse;
