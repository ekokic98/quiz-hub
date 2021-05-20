import React from 'react';
import { Card } from "antd";

const { Meta } = Card;

const MyCard = ({ onClick, imgSrc, style, title, description }) => {
    return (
        <Card
            hoverable
            style={style}
            cover={imgSrc && <img alt={title} src={imgSrc} />}
            onClick={onClick}
        >
            <Meta title={title} description={description} />
        </Card>
    );
}

export default MyCard;
