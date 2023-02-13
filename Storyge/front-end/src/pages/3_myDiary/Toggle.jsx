import React from "react";
import Form from "react-bootstrap/Form";
import "bootstrap/dist/css/bootstrap.min.css";

export default function Toggle() {
  return (
    <Form>
      <Form.Check type="switch" id="custom-switch" />
    </Form>
  );
}
