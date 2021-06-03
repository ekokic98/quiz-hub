import React from 'react';
import { Card } from "antd";
import placeholderImage from 'assets/images/placeholder.png';

import "./myCard.scss";

const { Meta } = Card;

const MyCard = ({ onClick, imgSrc, style, title, description }) => {
    return (
        <Card
            hoverable
            style={style}
            cover={ imgSrc ? <img alt={ title } src={ imgSrc }/> : !isNaN(title) ?
                <div className="my-card-cover" style={{ display: "flex", backgroundColor: "#" + (title * 100000).toString(16).padStart(6, '0') }}>
                    { title }
                </div> : <img alt="Placeholder" src={ placeholderImage }/>
            }
            onClick={onClick}
        >
            <Meta title={title} description={description} />
        </Card>
    );
}

export default MyCard;
