import React, {useEffect, useState} from 'react';
import {Col, Row} from "antd";
import MyCard from "components/MyCard";
import { getAllCategories } from "api/quiz/categories";

import "./categories.scss";

const Categories = () => {
    const [categories, setCategories] = useState([]);

    useEffect(() => {
        const fetchData = async () => setCategories(await getAllCategories())

        fetchData();
    }, [])

    return (
        <div className="categories-container">
            <Row gutter={[32, 32]}>
                {categories.map(category =>
                    <Col xs={12} sm={8} md={4}>
                        <MyCard key={category.id} imgSrc={category.imageUrl} title={category.name} />
                    </Col>
                )}
            </Row>
        </div>
    );
}

export default Categories;
