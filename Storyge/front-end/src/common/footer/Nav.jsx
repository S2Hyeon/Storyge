import "bootstrap/dist/css/bootstrap.min.css";
import { TbHome, TbMusic, TbPlus, TbBell, TbUser } from "react-icons/tb";

import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";

function Footer() {
  return (
    <>
      <Navbar
        bg="white"
        variant="light"
        fixed="bottom"
        style={{
          height: "6rem",
          background: "#ffffff",
          boxShadow: "0px 2px 15px rgba(184, 184, 210, 0.5)",
        }}
      >
        <Container>
          <Nav className="me-auto">
            <Nav.Link href="#home">
              <TbHome className="icons" />
            </Nav.Link>
            <Nav.Link href="#music">
              <TbMusic className="icons" />
            </Nav.Link>
            <Nav.Link href="#write">
              <TbPlus className="icons" />
            </Nav.Link>
            <Nav.Link href="#alert">
              <TbBell className="icons" />
            </Nav.Link>
            <Nav.Link href="#mypage">
              <TbUser className="icons" />
            </Nav.Link>
          </Nav>
        </Container>
      </Navbar>
    </>
  );
}

export default Footer;
