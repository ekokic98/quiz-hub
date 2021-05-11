import React from 'react';
import { Col, Row } from "antd";

import './footer.scss';

const Footer = () => {
    return (
        <Row className="footer-container" align="middle">
            <Col span={8}>
                Project by:
                <div className="project-names">
                    <span>
                        Amra Musić
                    </span>
                    <span>
                        Kerim Kadušić
                    </span>
                    <span>
                        Lino Bevanda
                    </span>
                    <span>
                        Faris Poljčić
                    </span>
                </div>
            </Col>

            <Col span={8}>
                QuizHub
            </Col>

            <Col span={8}>
                NWT 2021
            </Col>
        </Row>
    );
}

export default Footer;
