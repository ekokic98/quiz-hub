import React from 'react';
import { Card } from "antd";

const { Meta } = Card;

const MyCard = ({ imgSrc, style, title, description }) => {
    return (
        <Card
            hoverable
            style={style}
            cover={<img alt={title} src={imgSrc} />}
        >
            <Meta title={title} description={description} />
        </Card>
    );
}

export default MyCard;
