import React from "react";

function Collapse({ title, id, children }) {
  return (
    <>
      <div
        className="text-white my-2"
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

      <div className="collapse" id={id}>
        <div className="card card-body bg-transparent">{children}</div>
      </div>
    </>
  );
}

export default Collapse;
