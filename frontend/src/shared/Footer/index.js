import React from 'react';
import {Button, Col, Row} from "antd";
import {GithubFilled} from '@ant-design/icons';

import './footer.scss';

const Footer = () => {
    return (
        <Row className="footer-container" align="middle">
            <Col span={8}>
                <div className="project-names">
                    <span>
                        Made with ❤️ by:
                    </span>
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
                © QuizHub
                <Button type="text" href={"https://github.com/kkadusic/quiz-hub"}>
                    <GithubFilled className="footer-icon"/>
                </Button>
            </Col>

            <Col span={8}>
                NWT 2021
            </Col>
        </Row>
    );
}

export default Footer;
